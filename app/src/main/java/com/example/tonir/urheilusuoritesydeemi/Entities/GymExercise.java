package com.example.tonir.urheilusuoritesydeemi.Entities;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonSets;

public class GymExercise extends BaseExercise {
    private GymSet set;
    private ButtonSets weights;
    private ButtonSets repeats;

    public GymExercise() {
    }

    public GymExercise(ExerciseType exerciseType) {
        this.set = new GymSet();
        this.exerciseType = exerciseType;
    }

    //region getter/setter
    public GymSet getSet() {
        return set;
    }

    public void setSet(GymSet set) {
        this.set = set;
    }

    public ButtonSets getWeights() {
        return weights;
    }

    public void setWeights(ButtonSets weights) {
        this.weights = weights;
    }

    public ButtonSets getRepeats() {
        return repeats;
    }

    public void setRepeats(ButtonSets repeats) {
        this.repeats = repeats;
    }
    //endregion

    @Override
    public String getFireBaseEntityName() {
        return "GymExercise";
    }
}
