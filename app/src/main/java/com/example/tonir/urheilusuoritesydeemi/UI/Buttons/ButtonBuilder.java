package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;

public class ButtonBuilder {
    public static BaseButton getButton(Context context, ButtonParameters parameters, LinearLayout root, BaseButton.ButtonListener listener) {
        if (parameters.getButtonType() != null) {
            switch (parameters.getButtonType()) {
                case TAG:
                    return new TagButton(context, parameters, root, listener);
                case TEXT:
                    return new StringButton(context, parameters, root, listener);
                default:
                    return null;
            }
        }
        return null;
    }

    public static ButtonParameters getParameters(ButtonTag tag){
        ButtonParameters parameters = new ButtonParameters();
        parameters.setButtonTag(tag);
        return parameters;
    }
    public static ButtonParameters getParameters(String buttonText){
        ButtonParameters parameters = new ButtonParameters();
        parameters.setButtonText(buttonText);
        return parameters;
    }
}
/*
    private UUID identifier;
    private BaseExercise owner;
    private Boolean highlightOnClick;
    private Double value;
 */