package com.example.tonir.urheilusuoritesydeemi.Handler;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentBase;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FragmentHandler {
    private String TAG;
    private FragmentManager fragmentManager;
    private int fragmentContainer;
    private List<UUID> visibleFragmentTags;
    private static ConcurrentHashMap<UUID, Fragment> fragmentCache;

    public FragmentHandler(String TAG, FragmentManager fragmentManager, int fragmentContainer) {
        this.TAG = TAG;
        this.fragmentManager = fragmentManager;
        this.fragmentContainer = fragmentContainer;
        Init();
    }

    private void Init(){
        visibleFragmentTags = new ArrayList<>();
        fragmentCache = new ConcurrentHashMap<>();
    }

    public void DeleteAllFragments() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentManager.getFragments()) {
            transaction.remove(fragment);
        }
        transaction.commit();
        fragmentCache.clear();
        visibleFragmentTags.clear();
    }

    //region Fragment Visibility
    //region toggle
    public void toggle(FragmentParameters fragmentParameters, @Nullable BaseButton.ButtonClickListener clickCallback) throws NoSuchFieldException {
        if (visibleFragmentTags == null) {
            visibleFragmentTags = new ArrayList<>();
        }
        if (visibleFragmentTags.contains(fragmentParameters.getIdentifier())) {
            hide(fragmentParameters.getIdentifier());
        } else {
            show(fragmentParameters, clickCallback);
        }
    }
//endregion

    public void hide(UUID id) {
        try {
            Fragment fragment = fragmentManager.findFragmentByTag(id.toString());
            if (fragment == null) {
                fragment = FragmentFromCache(id);
            }
            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(fragment)
                        .commit();
                visibleFragmentTags.remove(id);
            }
        } catch (Exception e) {
            Log.e(TAG, "hide: " + id, e);
        }
    }

    public void show(UUID id) {
        try {
            Fragment fragment = fragmentManager.findFragmentByTag(id.toString());
            if (fragment == null) {
                fragment = FragmentFromCache(id);
            }
            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(fragment)
                        .commit();
                visibleFragmentTags.add(id);
            }
        } catch (Exception e) {
            Log.e(TAG, "show: " + id, e);
        }
    }

    public void hideAll() {
        try {
            for (UUID key : fragmentCache.keySet()) {
                if (visibleFragmentTags.contains(key)) {
                    hide(key);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "hideAll: ", e);
        }
    }

    public void hideAllExpect(UUID id) {
        try {
            for (UUID key : fragmentCache.keySet()) {
                if (!key.equals(id)) {
                    if (visibleFragmentTags.contains(key)) {
                        hide(key);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "hideAllExpect: " + id, e);
        }
    }

    public void show(FragmentParameters fragmentParameters, @Nullable BaseButton.ButtonClickListener clickCallback) throws NoSuchFieldException {
        boolean created = CreateFragmentIfNotExists(fragmentParameters, clickCallback);
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentParameters.getIdentifier().toString());
        if (fragment == null) {
            fragment = FragmentFromCache(fragmentParameters.getIdentifier());
        }
        if (fragment == null) {
            throw new NoSuchFieldException();
        }
        if (created) {
            add(fragment, fragmentParameters.getIdentifier());
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .show(fragment)
                    .commit();
            visibleFragmentTags.add(fragmentParameters.getIdentifier());
        }
    }

    public boolean FragmentIsVisible(UUID id) {
        return visibleFragmentTags.contains(id);
    }

    public void add(Fragment fragment, UUID id) {
        if (visibleFragmentTags == null) {
            visibleFragmentTags = new ArrayList<>();
        }
        if (fragment instanceof FragmentBase && ((FragmentBase) fragment).HasIdentifier()) {
            addFragmentToCache(fragment, id);
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .add(fragmentContainer, fragment, ((FragmentBase) fragment).GetIdentifier().toString())
                    .commit();
            visibleFragmentTags.add(id);
        } else {
            addFragmentToCache(fragment, id);
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .add(fragmentContainer, fragment, id.toString())
                    .commit();
            visibleFragmentTags.add(id);
        }
    }

    //endregion

    //region private
    private boolean CreateFragmentIfNotExists(FragmentParameters fragmentParameters, @Nullable BaseButton.ButtonClickListener clickCallback) {
        if (!FragmentExists(fragmentParameters.getIdentifier())) {
            CreateNewFragment(fragmentParameters, clickCallback);
            return true;
        }
        return false;
    }

    @Nullable
    private Fragment FragmentFromCache(UUID id) {
        return fragmentCache.get(id);
    }

    private boolean FragmentExists(UUID id) {
        return (fragmentCache != null && id != null && fragmentCache.containsKey(id));
    }

    private void CreateNewFragment(FragmentParameters fragmentParameters, @Nullable BaseButton.ButtonClickListener clickCallback) {
        try {
            Fragment fragment = FragmentBase.newInstance(fragmentParameters, clickCallback);
            addFragmentToCache(fragment, fragmentParameters.getIdentifier());
        } catch (ClassNotFoundException e) {
            Log.e(fragmentParameters.getFragmentType().toString(), "CreateNewFragment: " + fragmentParameters.GetAsString(), e);
        }
    }


    //endregion

    //region getters/setters
    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragmentToCache(Fragment fragment, UUID id) {
        if (fragmentCache == null) {
            fragmentCache = new ConcurrentHashMap<>();
        }
        fragmentCache.put(id, fragment);
    }

    public void removeFragmentFromCache(FragmentBase fragment, String tag) {
        fragmentCache.remove(tag, fragment);
    }

    public void destroyFragment(UUID id) {
        Fragment fragment = FragmentFromCache(id);
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentCache.remove(id, fragment);
        }
    }
    //endregion
}
