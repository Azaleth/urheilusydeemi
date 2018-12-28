package com.example.tonir.urheilusuoritesydeemi.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Entities.GymExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.example.tonir.urheilusuoritesydeemi.ViewModels.GymExerciseViewModel;

import java.util.UUID;

public class GymExerciseFragment extends FragmentBase<GymExerciseViewModel, GymExercise> {
    private static final String TAG = GymExerciseFragment.class.getSimpleName();
    public void showGymExerciseById(UUID id){
        showGymExerciseById(id, false);
    }
    public void showGymExerciseById(UUID id, boolean forceReload){
        viewModel.GetGymExercise(id, forceReload).observe(this, observer);;
    }
    public void showNewGymExerciseByType(ExerciseType type){
        viewModel.GetNewGymExerciseByType(type).observe(this, observer);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(GymExerciseViewModel.class);
        if (type == FragmentType.GYM_EXERCISE){
            showGymExerciseById(id);
        }
        if(type == FragmentType.NEW_GYM_EXERCISE){
            showNewGymExerciseByType(fragmentParameters.getExerciseType());
        }
    }
}
