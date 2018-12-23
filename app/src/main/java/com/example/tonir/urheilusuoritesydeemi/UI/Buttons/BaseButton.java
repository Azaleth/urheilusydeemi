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
import com.example.tonir.urheilusuoritesydeemi.UI.Events.BaseEventArgs;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.ButtonClickEventArgs;
import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

import java.util.UUID;

public class BaseButton
        extends AppCompatButton {

    private static final String TAG = BaseButton.class.getSimpleName();
    View view;
    LinearLayout root;
    NoEditStringField field;
    Button button;
    ButtonParameters parameters;
    ButtonListener listener;

    public void setSelected(boolean selected) {
        this.button.setSelected(selected);
    }

    public BaseButton(Context context, LinearLayout root) {
        super(context);
        this.root = root;

        if (parameters.getValue() == null) {
            try {
                parameters.setValue(Double.parseDouble(parameters.getButtonText()));
            } catch (Exception e) {
                Log.e(TAG, "BaseButton: ", e);
            }
        }
    }

    //region setter/getter
    public void setTag(ButtonTag tag) {
        this.parameters.setButtonTag(tag);
        if (this.button != null) {
            button.setText(tag.format(getContext()));
        }
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

    public ButtonParameters getParameters() {
        return parameters;
    }

    public void setParameters(ButtonParameters parameters) {
        this.parameters = parameters;
    }

    //endregion
    public interface ButtonListener {
        void ClickEvent(Object sender, BaseEventArgs args);
    }

    public void onClick(View v) {
        if (parameters.isHighlightOnClick()) {
            v.setSelected(!v.isSelected());
        }
        if (this.listener != null) {
            listener.ClickEvent(this, new ButtonClickEventArgs(this.parameters));
        }
    }
}
