package com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonType;

public class ButtonBarParameters {
    ButtonTag[] buttonTags;
    String[] buttonTexts;
    int buttonsOnRow = 3;
    ButtonTag selectedTag;
    String selectedButton;
    boolean SingleSelection = false;
    boolean highlightOnClick = true;

    public ButtonBarParameters(){}



    //region getter setter
    public ButtonType getButtonType(){
        if (this.buttonTags != null) {
            return ButtonType.TAG;
        } else if (this.buttonTexts != null) {
            return ButtonType.TEXT;
        }else{
            return null;
        }
    }

    public void setButtonInfos(Object[] texts)
    {
        if(texts.length == 0){
            throw new IllegalArgumentException();
        }
        if(texts instanceof ButtonTag[]){
            setButtonTags((ButtonTag[]) texts);
        }
        else if(texts instanceof String[]){
            setButtonTexts((String[])texts);
        }
    }

    public ButtonTag[] getButtonTags() {
        return buttonTags;
    }

    private void setButtonTags(ButtonTag[] buttonTags) {
        this.buttonTags = buttonTags;
    }

    public String[] getButtonTexts() {
        return buttonTexts;
    }

    private void setButtonTexts(String[] buttonTexts) {
        this.buttonTexts = buttonTexts;
    }

    public int getButtonsOnRow() {
        return buttonsOnRow;
    }

    public void setButtonsOnRow(int buttonsOnRow) {
        this.buttonsOnRow = buttonsOnRow;
    }

    public ButtonTag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(ButtonTag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public String getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(String selectedButton) {
        this.selectedButton = selectedButton;
    }

    public boolean isSingleSelection() {
        return SingleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        SingleSelection = singleSelection;
    }

    public boolean isHighlightOnClick() {
        return highlightOnClick;
    }

    public void setHighlightOnClick(boolean highlightOnClick) {
        this.highlightOnClick = highlightOnClick;
    }

    //endregion
}
