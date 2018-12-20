package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

import java.util.UUID;

public class BaseButton
        extends AppCompatButton {

    private static final String TAG = BaseButton.class.getSimpleName();
    View view;
    LinearLayout root;
    NoEditStringField field;
    Button button;
    double value;
    ButtonParameters parameters;

    public BaseButton(Context context, ButtonParameters parameters, LinearLayout root, @Nullable Integer value) {
        super(context);
        this.parameters = parameters;
        this.root = root;

        if (value != null) {
            this.value = value;
        } else {
            try {
                this.value = Double.parseDouble(parameters.getButtonText());
                if (this.value < 0) {
                    Log.e(TAG, "ButtonBarButton: TURHAA SPÄMMIÄ LUULTAVASTI");
                }
            } catch (Exception e) {
                Log.e(TAG, "ButtonBarButton: TURHAA SPÄMMIÄ LUULTAVASTI", e);
            }
        }
    }

    public void setSelected(boolean selected){
        this.button.setSelected(selected);
    }

    //region setter/getter
    public void setTag(ButtonTag tag) {
        this.parameters.setButtonTag(tag);
        if (this.button != null) {
            button.setText(tag.format(getContext()));
        }
    }

    public static String getTAG() {
        return TAG;
    }

    public void setView(View view) {
        this.view = view;
    }

    public LinearLayout getRoot() {
        return root;
    }

    public void setRoot(LinearLayout root) {
        this.root = root;
    }

    public NoEditStringField getField() {
        return field;
    }

    public void setField(NoEditStringField field) {
        this.field = field;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ButtonParameters getParameters() {
        return parameters;
    }

    public void setParameters(ButtonParameters parameters) {
        this.parameters = parameters;
    }
    //endregion
}
