package com.example.tonir.urheilusuoritesydeemi.Enums;

import android.content.Context;

public enum ButtonTag {
    GYM,
    RUN;

    public String format(Context context) {
        return this.toString();
    }

    public FragmentType getRelatedFragmentType() {
        switch (this) {
            case RUN:
                return FragmentType.RUN_EXERCISE;
            case GYM:
                return FragmentType.GYM_EXERCISE;
            default:
                return null;
        }
    }
}
