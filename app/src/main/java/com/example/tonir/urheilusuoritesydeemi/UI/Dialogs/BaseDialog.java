package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;

public abstract class BaseDialog extends AlertDialog
        implements DialogInterface.OnClickListener,
        Buttons.ButtonBarListener {
    String selectedValue;
    String[] buttonTexts;

    public BaseDialog(Context context) {
        super(context);
    }

    void BuildView(Context context) {
        ButtonBarBase buttonBarBase = new ButtonBarBase(context, this, buttonTexts);
        buttonBarBase.allowOnlySingleButtonSelected(true);

        setButton(BUTTON_POSITIVE, context.getString(R.string.ok), this);
        setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), this);

        setView(buttonBarBase.getLayout());
    }

    @Override
    public void onButtonBarButtonClicked(double amount, ButtonTag tag, String buttonText) {
        this.selectedValue = buttonText;
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
