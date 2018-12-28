package com.example.tonir.urheilusuoritesydeemi.Events;

public class DialogEvent extends BaseEvent {
    Object selection;
    public DialogEvent(Object selection){
        this.selection = selection;
    }

    public Object getSelection() {
        return selection;
    }
}
