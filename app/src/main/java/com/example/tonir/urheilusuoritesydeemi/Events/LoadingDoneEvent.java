package com.example.tonir.urheilusuoritesydeemi.Events;

import java.util.ArrayList;
import java.util.List;

public class LoadingDoneEvent extends BaseEvent {
    private List<Object> objects;

    public LoadingDoneEvent(List<Object> objects){
        this.objects = objects;
    }
    LoadingDoneEvent(Object object){
        if(this.objects == null){
            this.objects = new ArrayList<>();

        }
        this.objects.add(object);
    }

    public List<Object> getObjects() {
        return objects;
    }
}
