package com.example.tonir.urheilusuoritesydeemi.UI.Events;

import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;

public class ButtonClickEventArgs extends BaseEventArgs {
    private ButtonParameters buttonParameters;

    public ButtonClickEventArgs(ButtonParameters parameters) {
        this.buttonParameters = parameters;
    }

    //region getter/setter
    public ButtonParameters getButtonParameters() {
        return buttonParameters;
    }

    public void setButtonParameters(ButtonParameters buttonParameters) {
        this.buttonParameters = buttonParameters;
    }
    //endregion

}
