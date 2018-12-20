package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.R;

public class StringButton
        extends BaseButton
        implements View.OnClickListener {

    StringButtonClickListener listener;

    public StringButton(Context context, ButtonParameters parameters, LinearLayout rootView, @Nullable Integer value, StringButtonClickListener listener) {
        super(context, parameters, rootView, value);
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


    @Override
    public void onClick(View v) {
        if (parameters.isHighlightOnClick()) {
            v.setSelected(!v.isSelected());
        }
        if (this.listener != null) {
            listener.onClicked(this);
        }
    }

    public interface StringButtonClickListener {
        void onClicked(StringButton sender);
    }
}
