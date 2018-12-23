package com.example.tonir.urheilusuoritesydeemi.UI.Events;

public class BaseEvent {
    private BaseEventArgs eventArgs;

    public BaseEvent(BaseEventArgs eventArgs) {
        this.eventArgs = eventArgs;
    }

    public BaseEventArgs getEventArgs() {
        return eventArgs;
    }

    public void setEventArgs(BaseEventArgs eventArgs) {
        this.eventArgs = eventArgs;
    }
}
