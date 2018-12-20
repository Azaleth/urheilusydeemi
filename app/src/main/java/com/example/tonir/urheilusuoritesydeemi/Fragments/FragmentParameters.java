package com.example.tonir.urheilusuoritesydeemi.Fragments;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.EntityType;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.google.gson.GsonBuilder;

import java.util.UUID;

public class FragmentParameters {
    private final static String TAG = FragmentParameters.class.getSimpleName();
    private String fragmentTag;
    private UUID ownerId;
    private EntityType ownerEntityType;
    private FragmentType fragmentType;
    private String query;
    private String buttonTag;
    private UUID identifier;
    private String exerciseType;


    public FragmentParameters() {
    }

    //region basic getter/setter
    public String getFragmentTag() {
        return fragmentTag;
    }

    public void setFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
    }

    public FragmentType getFragmentType() {
        return fragmentType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getButtonTag() {
        return buttonTag;
    }

    public void setButtonTag(String buttonTag) {
        this.buttonTag = buttonTag;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    //endregion
    //region advanced getter/setter
    public ButtonTag GetButtonTag(){
        if(buttonTag != null) {
            return ButtonTag.valueOf(buttonTag);
        }
        else{
            return null;
        }
    }
    public FragmentType GetFragmentType(){
        return fragmentType;
    }

    public void setFragmentType(FragmentType type){
        this.fragmentType = type;
    }
    public void setButtonTag(ButtonTag tag){
        if(tag!= null) {
            this.buttonTag = tag.toString();
        }
    }
    public void setOwner(BaseExercise owner){
        if(owner != null && owner.getId() != null) {
            this.ownerId = owner.getId();
        }
        if(owner != null && owner.getEntityType() != null) {
            this.ownerEntityType = owner.getEntityType();
        }
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public EntityType getOwnerEntityType() {
        return ownerEntityType;
    }

    public void setOwnerEntityType(EntityType ownerEntityType) {
        this.ownerEntityType = ownerEntityType;
    }
    //endregion

    public String GetAsString() {
        return new GsonBuilder().create().toJson(this);
    }

    public static FragmentParameters ParseFromString(String string) {
        return new GsonBuilder().create().fromJson(string, FragmentParameters.class);
    }

    public boolean IsValid() {
        return !this.fragmentTag.isEmpty();
    }
}