package com.example.tonir.urheilusuoritesydeemi.Fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.tonir.urheilusuoritesydeemi.Activities.MainActivity;
import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.example.tonir.urheilusuoritesydeemi.Handler.ProgressHandler;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.Adapters.ExerciseListAdapter;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;

import java.util.List;
import java.util.UUID;

public abstract class FragmentBase<T extends ViewModel, Y extends BaseExercise>
        extends Fragment {

    //region properties
    private static final String TAG = FragmentBase.class.getSimpleName();
    FragmentType type;
    String exerciseType;
    UUID id;
    FrameLayout frameLayout;
    View infoView;
    View progressView;
    View currentObservableView;
    ListView listFrameLayout;
    T viewModel;
    Observer<Y> observer;
    Observer<List<Y>> listObserver;
    ExerciseListAdapter exerciseListAdapter;
    BaseButton.ButtonClickListener clickCallback;
    ButtonTag tag;
    FragmentParameters fragmentParameters;
    //endregion

    //region newInstance
    public static FragmentBase newInstance(FragmentParameters fragmentParameters, @Nullable BaseButton.ButtonClickListener clickCallback) throws ClassNotFoundException {
        FragmentBase fragment;
        switch (fragmentParameters.GetFragmentType()) {
            case GYM_EXERCISE:
            case NEW_GYM_EXERCISE:
                fragment = new GymExerciseFragment();
                break;
            default:
                Log.e(TAG, "newInstance: " + fragmentParameters.GetFragmentType());
                throw new ClassNotFoundException();
        }
        Bundle arguments = new Bundle();
        arguments.putString("type", fragmentParameters.getFragmentType().toString());
        arguments.putString("fragmentParameters", fragmentParameters.GetAsString());
        if (fragmentParameters.getIdentifier() != null) {
            arguments.putString("id", fragmentParameters.getIdentifier().toString());
        }
        if (fragmentParameters.getOwnerId() != null) {
            arguments.putString("ownerId", fragmentParameters.getOwnerId().toString());
        }
        if (fragmentParameters.getQuery() != null) {
            arguments.putString("query", fragmentParameters.getQuery());
        }
        if (fragmentParameters.getOwnerEntityType() != null) {
            arguments.putString("entityType", fragmentParameters.getOwnerEntityType().toString());
        }
        if (fragmentParameters.GetButtonTag() != null) {
            fragment.tag = fragmentParameters.GetButtonTag();
        }
        if (clickCallback != null) {
            fragment.clickCallback = clickCallback;
        }
        fragment.setArguments(arguments);

        return fragment;
    }
    //endregion

    void readArguments(Bundle args) {
        type = FragmentType.valueOf(args.getString("type"));
        if (args.containsKey("fragmentParameters")) {
            fragmentParameters = FragmentParameters.ParseFromString(args.getString("fragmentParameters"));
        }
        if (args.containsKey("id")) {
            id = UUID.fromString(args.getString("id"));
        }
        if (args.containsKey("buttonTag")) {
            tag = ButtonTag.valueOf(args.getString("buttonTag"));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.readArguments(getArguments());
        infoView = inflater.inflate(R.layout.information_fragment, container, false);
        progressView = infoView.findViewById(R.id.fragment_progress);
        frameLayout = infoView.findViewById(R.id.fragment_frame);
        listFrameLayout = infoView.findViewById(R.id.list_fragment_frame);
        showProgress(true);
        return infoView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setObserver();
        setListObserver();
    }

    void showProgress(final boolean show) {
        ProgressHandler.showProgress(show, frameLayout, progressView);
    }

    private void setObserver() {
        observer = new Observer<Y>() {
            @Override
            public void onChanged(Y observable) {
                showProgress(false);
                if (observable == null) {
                    Activity activity = getActivity();
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).requestDestroy(fragmentParameters.getIdentifier());
                    }
                }
                if (frameLayout.getVisibility() == View.INVISIBLE) {
                    frameLayout.setVisibility(View.VISIBLE);
                }
                if (!type.isNew() && frameLayout != null && observable != null) {
                    if (getActivity() instanceof MainActivity) {
                        observable.setListener(((MainActivity) getActivity()).getExerciseListener());
                    }
                    replaceView(observable.GetInformationLayout(getContext()));
                }
                if (type.isNew() && frameLayout != null && observable != null) {
                    if (getActivity() instanceof MainActivity) {
                        observable.setListener(((MainActivity) getActivity()).getExerciseListener());
                    }
                    if (getActivity() instanceof MainActivity) {
                        replaceView(observable.GetNewLayout(getContext(), ((MainActivity) getActivity()).getButtonBarListener()));
                    }
                }
            }
        };
    }

    private void setListObserver() {
        listObserver = new Observer<List<Y>>() {
            @Override
            public void onChanged(@Nullable List<Y> observables) {
                showProgress(false);
                if (observables == null) {
                    Activity activity = getActivity();
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).requestDestroy(fragmentParameters.getIdentifier());
                    }
                } else if (frameLayout != null && observables != null) {
                    if (listFrameLayout.getVisibility() == View.INVISIBLE || listFrameLayout.getVisibility() == View.GONE) {
                        listFrameLayout.setVisibility(View.VISIBLE);
                    }
                    if (exerciseListAdapter == null) {
                        exerciseListAdapter = new ExerciseListAdapter<>(getContext(), observables, clickCallback, tag);
                        listFrameLayout.setAdapter(exerciseListAdapter);
                    }
                    exerciseListAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(listFrameLayout);
                }
            }
        };
    }

    void replaceView(View newView) {
        if (currentObservableView != null) {
            int index = frameLayout.indexOfChild(currentObservableView);
            frameLayout.removeViewAt(index);
        }
        frameLayout.addView(newView);
        currentObservableView = newView;
    }

    public UUID GetIdentifier() {
        if (fragmentParameters != null) {
            return fragmentParameters.getIdentifier();
        } else {
            return null;
        }
    }

    public boolean HasIdentifier() {
        return (fragmentParameters != null);
    }

    // https://medium.com/@skidanolegs/listview-inside-scrollview-solve-the-problem-a06fdff2a4e0
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) view.setLayoutParams(new
                    ViewGroup.LayoutParams(desiredWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public interface FragmentListener {
        void listItemSelected(BaseExercise exercise);
    }
}
