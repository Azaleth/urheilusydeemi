package com.example.tonir.urheilusuoritesydeemi.UI.Buttons;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import com.example.tonir.urheilusuoritesydeemi.R;


public class ClickableButton extends android.support.v7.widget.AppCompatButton {
    private boolean selected = false;


    public ClickableButton(Context context) {
        super(context);
        super.setBackground(getResources().getDrawable(R.drawable.button, null));
    }

    public ClickableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setBackground(getResources().getDrawable(R.drawable.button, null));
    }

    public ClickableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setBackground(getResources().getDrawable(R.drawable.button, null));
    }

    public void toggleSelected(){
        this.selected = !this.selected;
        super.setSelected(this.selected);
    }
}
