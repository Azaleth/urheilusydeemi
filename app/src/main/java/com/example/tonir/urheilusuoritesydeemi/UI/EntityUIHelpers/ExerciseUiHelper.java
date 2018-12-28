package com.example.tonir.urheilusuoritesydeemi.UI.EntityUIHelpers;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Entities.GymExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonSets;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Helpers.LayoutHelper;
import com.example.tonir.urheilusuoritesydeemi.Helpers.LocalStorageHelper;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;
import com.google.firebase.database.Exclude;

public class ExerciseUiHelper {
    @Exclude
    public static LinearLayout GetNewGymExerciseLayout(Context context, GymExercise exercise) {
        LinearLayout linearLayout = LayoutHelper.GetBaseLinearLayout(context, LinearLayout.VERTICAL,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //weights layout
        linearLayout.addView(GetButtonsAndFieldView(exercise.getWeights() , context, "weights", exercise));

        //repeats layout
        linearLayout.addView(GetButtonsAndFieldView(exercise.getRepeats(), context, "repeats", exercise));

        //save layout
        LinearLayout saveLayout = LayoutHelper.GetBaseLinearLayout(context, LinearLayout.VERTICAL,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ButtonBarParameters parameters = getBarParameters(new ButtonTag[]{ButtonTag.SAVE});
        saveLayout.addView(ButtonBarBuilder.GetButtonBar(context, exercise, parameters).getLayout());
        saveLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(saveLayout);

        return linearLayout;
    }
    @Exclude
    private static LinearLayout GetButtonsAndFieldView(ButtonSets buttonSets, Context context, String tag, GymExercise exercise) {
        if (buttonSets == null) {
            buttonSets = ButtonSets.MEDIUM;
        }

        //Base layout
        LinearLayout linearLayout = LayoutHelper.GetBaseLinearLayout(context, LinearLayout.VERTICAL,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //header
        linearLayout.addView(LayoutHelper.GetBaseStringField(context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER, tag));

        //positive buttons
        ButtonBarParameters parameters = getBarParameters(buttonSets.GetButtonTexts(true));
        ButtonBarBase buttonBar = ButtonBarBuilder.GetButtonBar(context, exercise.getListener(), parameters);
        linearLayout.addView(buttonBar.getLayout());

        //Amount View
        linearLayout.addView(LayoutHelper.GetBaseStringField(context,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER,
                LocalStorageHelper.GetLastValueByExerciseType(exercise.getExerciseType().getExerciseType(), tag)));

        //Negative Buttons
        parameters = getBarParameters(buttonSets.GetButtonTexts(false));
        buttonBar = ButtonBarBuilder.GetButtonBar(context, exercise.getListener(), parameters);
        linearLayout.addView(buttonBar.getLayout());

        return linearLayout;
    }

    public static LinearLayout GetInformationLayout(Context context){
        return LayoutHelper.GetBaseLinearLayout(context, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Exclude
    private static ButtonBarParameters getBarParameters(Object[] buttonInfos) {
        ButtonBarParameters parameters = new ButtonBarParameters();
        parameters.setButtonInfos(buttonInfos);
        parameters.setButtonsOnRow(5);
        parameters.setHighlightOnClick(false);
        return parameters;
    }
}
