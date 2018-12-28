package com.example.tonir.urheilusuoritesydeemi.Fragments;

import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;

import java.util.UUID;

public class FragmentParameterHandler {
    public static FragmentParameters getFragmentParameters(ButtonTag tag) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setButtonTag(tag);
        fragmentParameters.setFragmentType(tag.getRelatedFragmentType());
        fragmentParameters.setIdentifier(UUID.randomUUID());
        return fragmentParameters;
    }

    public static FragmentParameters getFragmentParameters(ButtonTag tag, ExerciseType exerciseType) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setButtonTag(tag);
        fragmentParameters.setFragmentType(tag.getRelatedFragmentType());
        fragmentParameters.setIdentifier(UUID.randomUUID());
        fragmentParameters.setExerciseType(exerciseType);
        return fragmentParameters;
    }

    public static FragmentParameters getFragmentParameters(FragmentType tag) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setFragmentType(tag);
        fragmentParameters.setIdentifier(UUID.randomUUID());
        return fragmentParameters;
    }

    public static FragmentParameters getFragmentParameters(FragmentType tag, ExerciseType exerciseType, ExerciseSeries exerciseSeries) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setFragmentType(tag);
        fragmentParameters.setIdentifier(UUID.randomUUID());
        fragmentParameters.setExerciseType(exerciseType);
        fragmentParameters.setExerciseSeries(exerciseSeries);
        return fragmentParameters;
    }
}
