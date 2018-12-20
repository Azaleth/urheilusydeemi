package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;

import java.util.UUID;

public class ButtonParameters {
    private UUID identifier;
    private BaseExercise owner;
    private ButtonTag buttonTag;
    private String buttonText;
    private boolean highlightOnClick;

    public ButtonParameters(){}

    //region getter/setter

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public BaseExercise getOwner() {
        return owner;
    }

    public void setOwner(BaseExercise owner) {
        this.owner = owner;
    }

    public ButtonTag getButtonTag() {
        return buttonTag;
    }

    public void setButtonTag(ButtonTag buttonTag) {
        this.buttonTag = buttonTag;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public boolean isHighlightOnClick() {
        return highlightOnClick;
    }

    public void setHighlightOnClick(boolean highlightOnClick) {
        this.highlightOnClick = highlightOnClick;
    }

    //endregion
}
