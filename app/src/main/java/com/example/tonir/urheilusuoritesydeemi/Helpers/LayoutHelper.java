package com.example.tonir.urheilusuoritesydeemi.Helpers;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

public class LayoutHelper {


    public static LinearLayout GetBaseLinearLayout(Context context, int orientation, int widthParam, int heightParam){
        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setOrientation(orientation);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthParam, heightParam);
        linearLayout.setLayoutParams(params);

        return linearLayout;
    }

    public static NoEditStringField GetBaseStringField(Context context, int widthParam, int heightParam, int gravity, String tag){
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(widthParam, heightParam);
        textParams.gravity = gravity;

        NoEditStringField noEditStringField = new NoEditStringField(context);
        noEditStringField.setTag(tag);
        noEditStringField.editText.setText(tag.toUpperCase());
        noEditStringField.setLayoutParams(textParams);

        return noEditStringField;
    }
}
