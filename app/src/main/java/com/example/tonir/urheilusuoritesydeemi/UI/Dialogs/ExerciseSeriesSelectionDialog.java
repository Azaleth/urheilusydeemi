package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;

import java.util.List;

public class ExerciseSeriesSelectionDialog extends BaseDialog {

    public ExerciseSeriesSelectionDialog(@NonNull Context context, GeneralEventListener listener, List<ExerciseSeries> exerciseSeries) {
        super(context);
        this.listener = listener;

        String[] texts = new String[exerciseSeries.size()];
        for(int i = 0; i < exerciseSeries.size(); i++){
            texts[i] = exerciseSeries.get(i).getExerciseSeries();
        }

        this.errorText = "No Exercise Series Selected";

        buttonTexts = texts;
        BuildView(context);
    }
}
