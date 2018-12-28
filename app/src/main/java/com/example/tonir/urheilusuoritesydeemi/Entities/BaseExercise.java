package com.example.tonir.urheilusuoritesydeemi.Entities;

import android.util.Log;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Events.BaseEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.ButtonClickEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.google.firebase.database.Exclude;

public abstract class BaseExercise
        extends FirebaseEntity
        implements GeneralEventListener {
    static final String TAG = BaseExercise.class.getSimpleName();
    ExerciseType exerciseType;
    ExerciseSeries seriesType;

    @Exclude
    GeneralEventListener listener;

    //region getter/setter
    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public ExerciseSeries getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(ExerciseSeries seriesType) {
        this.seriesType = seriesType;
    }

    @Exclude
    public GeneralEventListener getListener() {
        return listener;
    }

    @Exclude
    public void setListener(GeneralEventListener listener) {
        this.listener = listener;
    }

    //endregion
    @Override
    public void Event(Object sender, BaseEvent event) {
        //TODO painon/toistojen määrät
        try {
            if (event instanceof ButtonClickEvent) {
                ButtonTag tag = ((ButtonClickEvent) event).getButtonParameters().getButtonTag();
                if (tag != null) {
                    switch (tag) {
                        case SAVE:
                            listener.Event(this, event);
                            break;
                        default:
                            if (listener != null) {
                                listener.Event(sender, event);
                            }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Event: ", e);
        }
    }

    @Override
    public boolean ownerIsCurrentUser() {
        return true;
    }
}
