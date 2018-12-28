package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import android.content.Context;

import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;

public class ButtonBarBuilder {
    public static ButtonBarBase GetButtonBar(Context context, GeneralEventListener listener, ButtonBarParameters parameters) {
        if (parameters != null) {
            switch (parameters.getButtonType()) {
                case TAG:
                    return new TagButtonBar(context, listener, parameters);
                case TEXT:
                    return new StringButtonBar(context, listener, parameters);
                default:
                    return null;
            }

        }
        return null;
    }
}
