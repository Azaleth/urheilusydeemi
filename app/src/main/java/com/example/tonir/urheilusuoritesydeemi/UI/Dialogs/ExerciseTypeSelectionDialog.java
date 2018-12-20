package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

public class ExerciseTypeSelectionDialog extends BaseDialog {

    private final OnExerciseSelectedListener exerciseTypeSelectedListener;

    public ExerciseTypeSelectionDialog(@NonNull Context context, OnExerciseSelectedListener exerciseTypeSelectedListener, String[] exerciseTypes) {
        super(context);
        this.exerciseTypeSelectedListener = exerciseTypeSelectedListener;
        buttonTexts = exerciseTypes;
        BuildView(context);
    }

    @Override
    public void show() {
        super.show();
        getButton(BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exerciseTypeSelectedListener != null) {
                    exerciseTypeSelectedListener.OnExerciseTypeSelected(selectedValue);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Exercise Not Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface OnExerciseSelectedListener {
        void OnExerciseTypeSelected(String exerciseType);
    }
}
