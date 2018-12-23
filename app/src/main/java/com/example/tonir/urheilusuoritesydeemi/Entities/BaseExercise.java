package com.example.tonir.urheilusuoritesydeemi.Entities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Enums.EntityType;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;

import java.util.UUID;

public class BaseExercise {
    private UUID id;
    private String name;
    private UUID exerciseGroup;
    private EntityType entityType;
    private String type;
    private BaseExerciseListener listener;

    //region getter/setter
    @Nullable
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getExerciseGroup() {
        return exerciseGroup;
    }

    public void setExerciseGroup(UUID exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseExerciseListener getListener() {
        return listener;
    }

    public void setListener(BaseExerciseListener listener) {
        this.listener = listener;
    }
    //endregion


    public LinearLayout     GetNewLayout(Context context, BaseButton.ButtonListener listener){
        return new LinearLayout(context);
    }
    public LinearLayout GetInformationLayout(Context context){
        return  new LinearLayout(context);
    }

    public interface BaseExerciseListener{
        void callback(BaseExercise caller, FragmentType type);
    }
}
