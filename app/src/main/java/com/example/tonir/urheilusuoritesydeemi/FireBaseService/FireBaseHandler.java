package com.example.tonir.urheilusuoritesydeemi.FireBaseService;

import android.support.annotation.Nullable;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class FireBaseHandler {
    private static final String TAG = FireBaseHandler.class.getSimpleName();

    private static FireBaseHandler instance;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    private FireBaseHandler() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
    }

    public static synchronized FireBaseHandler getInstance() {
        if (instance == null) {
            instance = new FireBaseHandler();
        }
        return instance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public DatabaseReference getReference() {
        return mFirebaseInstance.getReference();
    }

    public interface ExerciseLoadingComplete {
        void loaded(BaseExercise exercise);
    }

    public void save(BaseExercise exercise) {
        DatabaseReference dbRef = getReference().child(exercise.getType());
        String exerciseKey;
        if (exercise.getId() == null) {
            exerciseKey = dbRef.push().getKey();
        } else {
            exerciseKey = exercise.getId().toString();
        }
        dbRef.child(exercise.getType()).setValue(exercise);
    }

    public DatabaseReference getReference(String child) {
        return getReference().child(child);
    }

    public String[] getExerciseTypes() {
        //TODO
        return new String[]{
                "Penkki",
                "Jalkaprässi",
                "PlaceHolder",
                "New"
        };
    }

    public String[] getExerciseSeries(){
        //TODO
        return new String[]{
                "Jalat",
                "Selkä",
                "Placeholder",
                "New"
                };
    }
}