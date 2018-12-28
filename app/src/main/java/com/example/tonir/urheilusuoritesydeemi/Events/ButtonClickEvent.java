package com.example.tonir.urheilusuoritesydeemi.Events;

import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;

public class ButtonClickEvent extends BaseEvent {
    private ButtonParameters buttonParameters;

    public ButtonClickEvent(ButtonParameters parameters) {
        this.buttonParameters = parameters;
    }

    public ButtonParameters getButtonParameters() {
        return buttonParameters;
    }
}
