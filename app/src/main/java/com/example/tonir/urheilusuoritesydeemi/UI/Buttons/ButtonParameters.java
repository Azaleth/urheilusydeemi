package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.support.annotation.Nullable;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonType;

import java.util.UUID;
import java.util.stream.StreamSupport;

public class ButtonParameters {
    private String identifier;
    private BaseExercise owner;
    private ButtonTag buttonTag;
    private String buttonText;
    private Boolean highlightOnClick;
    private Double value;

    public ButtonParameters() {
    }


    public void parseParameters(Boolean highlightOnClick){
        parseParameters(highlightOnClick, null);
    }
    public void parseParameters(Boolean highlightOnClick, Double value){
        parseParameters(highlightOnClick, value, null);
    }
    public void parseParameters(Boolean highlightOnClick, Double value, String identifier){
        parseParameters(highlightOnClick, value, identifier, null);
    }
    public void parseParameters(Boolean highlightOnClick, Double value, String identifier, BaseExercise owner){
        this.setHighlightOnClick(highlightOnClick);
        this.setValue(value);
        this.setIdentifier(identifier);
        this.setOwner(owner);
    }


    //region getter/setter
    @Nullable
    public ButtonType getButtonType() {
        if (this.buttonTag != null) {
            return ButtonType.TAG;
        } else if (this.buttonText != null) {
            return ButtonType.TEXT;
        }else{
            return null;
        }
    }

    public String  getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String  identifier) {
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
        if(highlightOnClick == null){
            return  true;
        }
        return highlightOnClick;
    }

    public void setHighlightOnClick(Boolean highlightOnClick) {
        this.highlightOnClick = highlightOnClick;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
//endregion
}
