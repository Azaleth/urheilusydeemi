package com.example.tonir.urheilusuoritesydeemi.UI.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.tonir.urheilusuoritesydeemi.Events.BaseEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.ButtonClickEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.DialogEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;

import java.util.List;

public abstract class BaseDialog extends AlertDialog
        implements DialogInterface.OnClickListener,
        GeneralEventListener {
    private String selectedValue;
    String[] buttonTexts;
    GeneralEventListener listener;
    String errorText = "No selection";

    public BaseDialog(Context context) {
        super(context);
    }

    void BuildView(Context context) {
        ButtonBarParameters barParameters = new ButtonBarParameters();
        barParameters.setSingleSelection(true);
        barParameters.setButtonInfos(buttonTexts);
        ButtonBarBase buttonBarBase = ButtonBarBuilder.GetButtonBar(context, this, barParameters);

        setButton(BUTTON_POSITIVE, context.getString(R.string.ok), this);
        setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), this);

        setView(buttonBarBase.getLayout());
    }

    @Override
    public void Event(Object sender, BaseEvent event) {
        if (event instanceof ButtonClickEvent) {
            this.selectedValue = ((ButtonClickEvent) event).getButtonParameters().getButtonText();
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
    public void show() {
        super.show();
        getButton(BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.Event(this, new DialogEvent(selectedValue));
                    dismiss();
                } else {
                    Toast.makeText(getContext(), errorText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
