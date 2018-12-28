package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.Helpers.DoubleHelper;
import com.example.tonir.urheilusuoritesydeemi.Events.BaseEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.ButtonClickEvent;
import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

public class BaseButton
        extends AppCompatButton
        implements View.OnClickListener {

    private static final String TAG = BaseButton.class.getSimpleName();
    View view;
    LinearLayout root;
    NoEditStringField field;
    Button button;
    ButtonParameters parameters;
    GeneralEventListener listener;

    public void setSelected(boolean selected) {
        this.button.setSelected(selected);
    }

    public BaseButton(Context context, LinearLayout root, ButtonParameters parameters) {
        super(context);
        this.parameters = parameters;
        this.root = root;

        if (parameters.getValue() == null && parameters.getButtonText() != null) {
            parameters.setValue(DoubleHelper.parseDouble(parameters.getButtonText()));
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

    public void onClick(View v) {
        if (parameters.isHighlightOnClick()) {
            v.setSelected(!v.isSelected());
        }
        if (this.listener != null) {
            listener.Event(this, new ButtonClickEvent(this.parameters));
        }
    }
}
