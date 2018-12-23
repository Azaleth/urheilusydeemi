package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.ButtonClickEventArgs;

public class TagButton
        extends BaseButton
        implements View.OnClickListener {

    ButtonListener listener;

    public TagButton(Context context, ButtonParameters parameters, LinearLayout rootView, ButtonListener listener) {
        super(context, rootView);

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
            if (parameters.getButtonTag() != null) {
                button.setText(parameters.getButtonTag().format(getContext()));
            } else button.setText("null");
        }
    }
}