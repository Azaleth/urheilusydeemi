package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.BaseEventArgs;

import java.util.List;

public class ButtonBarBase
        extends LinearLayout {

    static final String TAG = ButtonBarBase.class.getSimpleName();
    Context context;
    BaseButton.ButtonListener listener;

    LinearLayout view;
    List<BaseButton> buttons;
    LinearLayout layout;

    ButtonBarParameters buttonBarParameters;

    ButtonBarBase(Context context) {
        super(context);
    }

    public ButtonBarBase(Context context, ButtonBarParameters buttonBarParameters) {
        super(context);
        this.buttonBarParameters = buttonBarParameters;
    }

    public void allowOnlySingleButtonSelected(boolean value) {
        this.buttonBarParameters.setSingleSelection(value);
    }

    public LinearLayout getLayout() {
        return this.layout;
    }

    void inflateLayout(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            view = (LinearLayout) layoutInflater.inflate(R.layout.button_bar_row, this);
        }
    }
}
