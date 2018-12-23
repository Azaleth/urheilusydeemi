package com.example.tonir.urheilusuoritesydeemi.Entities;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonSets;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Handler.LayoutHelper;
import com.example.tonir.urheilusuoritesydeemi.Handler.LocalStorageHelper;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.StringButtonBar;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.StringButton;

public class GymExercise extends BaseExercise {
    private GymSet set;
    private ButtonSets weights;
    private ButtonSets repeats;
    private BaseButton.ButtonListener listener;

    public GymExercise(String exerciseType) {
        this.set = new GymSet();
        this.set.setExerciseType(exerciseType);
    }

    public GymExercise() {

    }

    //region getter/setter
    public String getExerciseType() {
        return this.set.getExerciseType();
    }

    public void setExerciseType(String exerciseType) {
        this.set.setExerciseType(exerciseType);
    }

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
    public LinearLayout GetNewLayout(Context context, BaseButton.ButtonListener listener) {
        this.listener = listener;
        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);

        linearLayout.addView(GetButtonsAndFieldView(weights, context, "weights"));
        linearLayout.addView(GetButtonsAndFieldView(repeats, context, "repeats"));

        ButtonParameters parameters = new ButtonParameters();
        parameters.setButtonText("Save");

        linearLayout.addView(ButtonBuilder.getButton(context, parameters, linearLayout, listener));

        return linearLayout;
    }

    private LinearLayout GetButtonsAndFieldView(ButtonSets buttonSets, Context context, String tag) {
        if (buttonSets == null) {
            buttonSets = ButtonSets.MEDIUM;
        }

        //Base layout
        LinearLayout linearLayout = LayoutHelper.GetBaseLinearLayout(context, LinearLayout.VERTICAL,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayout.addView(LayoutHelper.GetBaseStringField(context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER, tag));

        ButtonBarParameters parameters = new ButtonBarParameters();
        parameters.setButtonTexts(buttonSets.GetButtonTexts(true));
        parameters.setButtonsOnRow(5);
        parameters.setHighlightOnClick(false);

        StringButtonBar stringButtonBar = new StringButtonBar(context, listener, parameters);
        linearLayout.addView(stringButtonBar.getLayout());

        linearLayout.addView(LayoutHelper.GetBaseStringField(context,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER,
                LocalStorageHelper.GetLastValueByExerciseType(getExerciseType(), tag)));

        parameters.setButtonTexts(buttonSets.GetButtonTexts(false));
        stringButtonBar = new StringButtonBar(context, listener, parameters);
        linearLayout.addView(stringButtonBar.getLayout());
        return linearLayout;
    }
}
