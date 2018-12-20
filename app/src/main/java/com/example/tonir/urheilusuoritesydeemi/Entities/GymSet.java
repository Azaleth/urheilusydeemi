package com.example.tonir.urheilusuoritesydeemi.Entities;

public class GymSet {
    private int repeats;
    private double weights;
    private String exerciseType;

    public GymSet(){}

    //region getter/setter

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    //endregion
}
