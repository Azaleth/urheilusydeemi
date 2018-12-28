package com.example.tonir.urheilusuoritesydeemi.Entities;

public class ExerciseType
        extends FirebaseEntity
        implements java.io.Serializable {
    private String exerciseType;
    private String exerciseSeriesIdentifier;

    public ExerciseType() {
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getExerciseSeriesIdentifier() {
        return exerciseSeriesIdentifier;
    }

    public void setExerciseSeriesIdentifier(String exerciseSeriesIdentifier) {
        this.exerciseSeriesIdentifier = exerciseSeriesIdentifier;
    }

    @Override
    public String getFireBaseEntityName() {
        return "ExerciseType";
    }

    @Override
    public boolean ownerIsCurrentUser() {
        return true;
    }
}
