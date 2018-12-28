package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.TagButton;

import java.util.ArrayList;
import java.util.Arrays;

public class TagButtonBar
        extends ButtonBarBase {

    public TagButtonBar(Context context, GeneralEventListener listener, ButtonBarParameters parameters) {
        super(context, parameters);
        this.listener = listener;
        buildLayout(parameters.buttonTags);
    }

    public void setSelected(ButtonTag tag) {
        for (BaseButton button : buttons) {
            if (button.getTag() == tag) {
                button.setSelected(true);
            }
        }
    }

    void buildLayout(@Nullable ButtonTag[] tags) {
        if (tags == null) {
            return;
        }
        LinearLayout buttonBar = new LinearLayout(context);
        buttonBar.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        buttonBar.setLayoutParams(params);

        if (tags.length > 0) {
            for (int i = 0; tags.length > i; i += buttonBarParameters.buttonsOnRow) {
                if (i + buttonBarParameters.buttonsOnRow < tags.length) {
                    buttonBar.addView(getButtonBarRow(Arrays.copyOfRange(tags, i, i + buttonBarParameters.buttonsOnRow)));
                } else {
                    buttonBar.addView(getButtonBarRow(Arrays.copyOfRange(tags, i, tags.length)));
                }
            }
        }
        this.layout = buttonBar;
    }

    private LinearLayout getButtonBarRow(ButtonTag[] tags) {
        if (buttons == null) {
            buttons = new ArrayList<>();
        }
        LinearLayout buttonRow = new LinearLayout(context);
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        for (ButtonTag tag : tags) {
            ButtonParameters parameters =  ButtonBuilder.getParameters(tag);
            parameters.parseParameters(this.buttonBarParameters.highlightOnClick);
            buttons.add(ButtonBuilder.getButton(context, parameters, buttonRow, listener));
        }
        return buttonRow;
    }
}
