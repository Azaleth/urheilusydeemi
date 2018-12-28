package com.example.tonir.urheilusuoritesydeemi.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Entities.GymExercise;
import com.example.tonir.urheilusuoritesydeemi.Handler.FireBaseHandler;

import java.util.UUID;

public class GymExerciseViewModel extends ViewModel implements FireBaseHandler.ExerciseLoadingComplete {
    private static final String TAG = GymExerciseViewModel.class.getSimpleName();
    private MutableLiveData<GymExercise> gymExercise;

    public MutableLiveData<GymExercise> GetGymExercise(UUID identifier, boolean forceReload) {
        if (gymExercise == null) {
            gymExercise = new MutableLiveData<>();
            LoadGymExercise(identifier);
        }
        if (forceReload) {
            LoadGymExercise(identifier);
        }
        return gymExercise;
    }

    public MutableLiveData<GymExercise> GetNewGymExerciseByType(ExerciseType type) {
        gymExercise = new MutableLiveData<>();
        CreateNewExercise(type);

        return gymExercise;
    }

    private void LoadGymExercise(UUID id) {
        //FireBaseHandler
        //TODO
    }

    private void CreateNewExercise(ExerciseType exerciseType) {
        gymExercise.postValue(new GymExercise(exerciseType));
    }

    @Override
    public void loaded(BaseExercise exercise) {
        if (exercise instanceof GymExercise) {
            this.gymExercise.postValue((GymExercise) exercise);
        }
    }
}
