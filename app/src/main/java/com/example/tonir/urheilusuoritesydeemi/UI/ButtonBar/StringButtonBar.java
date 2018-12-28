package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.StringButton;

import java.util.ArrayList;
import java.util.Arrays;

public class StringButtonBar
        extends ButtonBarBase{


    public StringButtonBar(Context context, GeneralEventListener listener, ButtonBarParameters parameters) {
        super(context, parameters);
        this.listener = listener;
        buildLayout(parameters.buttonTexts);
    }

    void buildLayout(@Nullable String[] texts) {
        if (texts == null) {
            return;
        }
        LinearLayout buttonBar = new LinearLayout(context);
        buttonBar.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        buttonBar.setLayoutParams(params);

        if (texts.length > 0) {
            for (int i = 0; texts.length > i; i += buttonBarParameters.buttonsOnRow) {
                if (i + buttonBarParameters.buttonsOnRow < texts.length) {
                    buttonBar.addView(getButtonBarRow(Arrays.copyOfRange(texts, i, i + buttonBarParameters.buttonsOnRow)));
                } else {
                    buttonBar.addView(getButtonBarRow(Arrays.copyOfRange(texts, i, texts.length)));
                }
            }
        }
        this.layout = buttonBar;
    }

    private LinearLayout getButtonBarRow(String[] texts) {
        if (buttons == null) {
            buttons = new ArrayList<>();
        }
        LinearLayout buttonRow = new LinearLayout(context);
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        for (String text : texts) {
            ButtonParameters parameters = new ButtonParameters();
            parameters.setButtonText(text);
            buttons.add(ButtonBuilder.getButton(context, parameters, buttonRow, listener));
        }
        return buttonRow;
    }

    public void setSelected(String buttonText) {
        for (BaseButton button : buttons) {
            if (button.getText() == buttonText) {
                button.setSelected(true);
            }
        }
    }

}
