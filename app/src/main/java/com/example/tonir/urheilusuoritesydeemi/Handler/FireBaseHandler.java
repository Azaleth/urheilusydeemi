package com.example.tonir.urheilusuoritesydeemi.Handler;

import android.support.annotation.NonNull;

import com.example.tonir.urheilusuoritesydeemi.BuildConfig;
import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FireBaseHandler {
    private static final String TAG = FireBaseHandler.class.getSimpleName();

    private static FireBaseHandler instance;
    private static FirebaseDatabase mFirebaseInstance;
    private static String userId;
    private static List<ExerciseSeries> exerciseSeries;

    private FireBaseHandler() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
    }

    public static synchronized FireBaseHandler getInstance() {
        if (instance == null) {
            instance = new FireBaseHandler();
        }
        return instance;
    }

    public static void setUserId(String userId) {
        FireBaseHandler.userId = userId;
        refreshExerciseSeries();
    }

    public static String getUserId() {
        return userId;
    }

    public static DatabaseReference getReference() {
        return mFirebaseInstance.getReference();
    }

    public interface ExerciseLoadingComplete {
        void loaded(BaseExercise exercise);
    }

    private static void refreshExerciseSeries() {
        DatabaseReference dbRef;
        if (BuildConfig.DEBUG) {
            dbRef = getReference().child("DEBUG").child("ExerciseSeries");
        } else {
            dbRef = getReference().child("ExerciseSeries");
        }

        dbRef.orderByChild("ownerId").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ExerciseSeries> exerciseSeries = new ArrayList<>();
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    exerciseSeries.add((ExerciseSeries) iterator.next());
                }
                FireBaseHandler.updateExerciseSeries(exerciseSeries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private static void updateExerciseSeries(List<ExerciseSeries> allSeries) {
        List<ExerciseSeries> mainSeries = FireBaseHandler.exerciseSeries;
        if(mainSeries  == null){
            mainSeries  = new ArrayList<>();
        }
        // loopataan uudet sarjat läpi
        for (ExerciseSeries series : allSeries) {
            int index = mainSeries .indexOf(series);
            if (index != -1) {
                //jos sarja on jo lisätty käydään läpi onko kaikki tyypit jo olemassa
                for (ExerciseType type : series.getExerciseTypes()) {
                    //Jos tyyppiä ei ole olemassa, lisätään se
                    if (!mainSeries .get(index).getExerciseTypes().contains(type)) {
                        mainSeries .get(index).getExerciseTypes().add(type);
                    }
                }
            } else {
                //jos sarjaa ei ole, lisätään se
                mainSeries .add(series);
            }
        }
        FireBaseHandler.exerciseSeries = mainSeries;
    }

    public List<ExerciseType> getExerciseTypes(String exerciseSeries) {
        int index = FireBaseHandler.exerciseSeries.indexOf(exerciseSeries);
        if (index != -1) {
            return FireBaseHandler.exerciseSeries.get(index).getExerciseTypes();
        } else {
            return null;
        }
    }

    public List<ExerciseSeries> getExerciseSeries() {
        return exerciseSeries;
    }
}