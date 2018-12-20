package com.example.tonir.urheilusuoritesydeemi.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.R;


public class NoEditStringField extends LinearLayout {
    public EditText editText;
    private String TAG;


    public NoEditStringField(Context context) {
        super(context);
        inflateLayout(context);
    }

    public NoEditStringField(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public NoEditStringField(Context context, String tag) {
        super(context);
        this.TAG = tag;
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            View view = layoutInflater.inflate(R.layout.field_view, this);

            this.editText = view.findViewById(R.id.field);
            this.editText.setTag(this.TAG);
            this.editText.setFocusable(false);
        }
    }
}
