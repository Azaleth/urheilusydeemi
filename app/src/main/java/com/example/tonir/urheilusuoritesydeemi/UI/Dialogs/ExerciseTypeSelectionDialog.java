package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;

import java.util.List;

public class ExerciseTypeSelectionDialog extends BaseDialog {

    public ExerciseTypeSelectionDialog(@NonNull Context context, GeneralEventListener listener, List<ExerciseType> exerciseTypes) {
        super(context);
        this.listener = listener;

        String[] texts = new String[exerciseTypes.size()];
        for(int i = 0; i < exerciseTypes.size(); i++){
            texts[i] = exerciseTypes.get(i).getExerciseType();
        }

        buttonTexts = texts;
        this.errorText = "No Exercise Type Selected";
        BuildView(context);
    }
}
