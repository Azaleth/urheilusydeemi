package com.example.tonir.urheilusuoritesydeemi.Fragments;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.google.gson.GsonBuilder;

import java.util.UUID;

public class FragmentParameters {
    private final static String TAG = FragmentParameters.class.getSimpleName();
    private String fragmentTag;
    private String ownerId;
    private FragmentType fragmentType;
    private String query;
    private String buttonTag;
    private UUID identifier;
    private ExerciseType exerciseType;
    private ExerciseSeries exerciseSeries;


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
        if(owner != null && owner.getOwnIdentifier() != null) {
            this.ownerId = owner.getOwnIdentifier();
        }
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public ExerciseSeries getExerciseSeries() {
        return exerciseSeries;
    }

    public void setExerciseSeries(ExerciseSeries exerciseSeries) {
        this.exerciseSeries = exerciseSeries;
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