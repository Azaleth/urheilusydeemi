package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;

import java.util.List;

public class ButtonBarBase
        extends LinearLayout {

    static final String TAG = ButtonBarBase.class.getSimpleName();
    Context context;
    GeneralEventListener listener;

    LinearLayout view;
    List<BaseButton> buttons;
    LinearLayout layout;

    ButtonBarParameters buttonBarParameters;

    ButtonBarBase(Context context) {
        super(context);
    }

    public ButtonBarBase(Context context, ButtonBarParameters buttonBarParameters) {
        super(context);
        this.context = context;
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
