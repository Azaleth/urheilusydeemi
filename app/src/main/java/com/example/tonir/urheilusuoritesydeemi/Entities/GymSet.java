package com.example.tonir.urheilusuoritesydeemi.Entities;

public class GymSet {
    private int repeats;
    private double weights;

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
    //endregion
}
