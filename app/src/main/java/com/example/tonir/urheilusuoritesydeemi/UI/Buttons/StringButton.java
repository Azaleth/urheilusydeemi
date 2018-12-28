package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.R;

public class StringButton
        extends BaseButton {



    public StringButton(Context context, ButtonParameters parameters, LinearLayout rootView, GeneralEventListener listener) {
        super(context, rootView, parameters);
        this.parameters = parameters;
        this.listener = listener;
        inflate();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.button_bar_button, root);
            button = view.findViewById(R.id.button_bar_button);
            button.setId(View.generateViewId());
            button.setOnClickListener(this);
            if (parameters.getButtonText() != null) {
                button.setText(parameters.getButtonText());
            } else {
                button.setText("null");
            }
        }
    }




    public interface StringButtonClickListener {
        void onClicked(StringButton sender);
    }
}
