package com.example.tonir.urheilusuoritesydeemi.Entities;

import java.util.List;

public class ExerciseSeries
        extends FirebaseEntity
        implements java.io.Serializable {
    private String ownerId;
    private String exerciseSeries;
    private List<ExerciseType> exerciseTypes;

    public ExerciseSeries() {
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getExerciseSeries() {
        return exerciseSeries;
    }

    public void setExerciseSeries(String exerciseSeries) {
        this.exerciseSeries = exerciseSeries;
    }

    public List<ExerciseType> getExerciseTypes() {
        return exerciseTypes;
    }

    public void setExerciseTypes(List<ExerciseType> exerciseTypes) {
        this.exerciseTypes = exerciseTypes;
    }

    @Override
    public String getFireBaseEntityName() {
        return "ExerciseSeries";
    }

    @Override
    public boolean ownerIsCurrentUser() {
        return true;
    }
}
