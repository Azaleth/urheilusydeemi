package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

public class ExerciseSeriesSelectionDialog extends BaseDialog {
    private final OnExerciseSeriesSelectedListener OnExerciseSeriesTypeSelected;

    public ExerciseSeriesSelectionDialog(@NonNull Context context, OnExerciseSeriesSelectedListener OnExerciseSeriesTypeSelected, String[] exerciseTypes) {
        super(context);
        this.OnExerciseSeriesTypeSelected = OnExerciseSeriesTypeSelected;
        buttonTexts = exerciseTypes;
        BuildView(context);
    }

    @Override
    public void show() {
        super.show();
        getButton(BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnExerciseSeriesTypeSelected != null) {
                    OnExerciseSeriesTypeSelected.OnExerciseSeriesTypeSelected(selectedValue);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Exercise Not Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface OnExerciseSeriesSelectedListener {
        void OnExerciseSeriesTypeSelected(String exerciseType);
    }
}
