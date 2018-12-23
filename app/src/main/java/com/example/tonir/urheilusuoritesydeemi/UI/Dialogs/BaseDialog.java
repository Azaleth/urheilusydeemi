package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.BaseEventArgs;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.ButtonClickEventArgs;

public abstract class BaseDialog extends AlertDialog
        implements DialogInterface.OnClickListener,
        BaseButton.ButtonListener {
    String selectedValue;
    String[] buttonTexts;

    public BaseDialog(Context context) {
        super(context);
    }

    void BuildView(Context context) {
        ButtonBarParameters barParameters = new ButtonBarParameters();
        barParameters.setSingleSelection(true);
        barParameters.setButtonTexts(buttonTexts);
        ButtonBarBase buttonBarBase = new ButtonBarBase(context, barParameters);

        setButton(BUTTON_POSITIVE, context.getString(R.string.ok), this);
        setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), this);

        setView(buttonBarBase.getLayout());
    }

    @Override
    public void ClickEvent(Object sender, BaseEventArgs args) {
        if (args instanceof ButtonClickEventArgs) {
            this.selectedValue = ((ButtonClickEventArgs) args).getButtonParameters().getButtonText();
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_NEGATIVE:
                cancel();
                break;
        }
    }
}
