package com.example.tonir.urheilusuoritesydeemi.Enums;

public enum FragmentType {
    NEW_GYM_EXERCISE,
    GYM_EXERCISE,
    NEW_RUN_EXERCISE,
    RUN_EXERCISE,
    CREATE_NEW;

    public boolean isNew() {
        switch (this) {
            case NEW_GYM_EXERCISE:
            case NEW_RUN_EXERCISE:
            case CREATE_NEW:
                return true;
            default:
                return false;
        }
    }
}
