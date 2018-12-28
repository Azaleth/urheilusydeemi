package com.example.tonir.urheilusuoritesydeemi.Entities;

import com.example.tonir.urheilusuoritesydeemi.BuildConfig;
import com.example.tonir.urheilusuoritesydeemi.Handler.FireBaseHandler;
import com.google.firebase.database.DatabaseReference;

public abstract class FirebaseEntity {
    String ownIdentifier;
    String parentIdentifier;
    String owner;
    Long CreatedOn;

    public abstract String getFireBaseEntityName();
    public abstract boolean ownerIsCurrentUser();

    public FirebaseEntity(){
        this.CreatedOn = System.currentTimeMillis();
    }

    //region getter/setter
    public String getOwnIdentifier() {
        return ownIdentifier;
    }

    public void setOwnIdentifier(String ownIdentifier) {
        this.ownIdentifier = ownIdentifier;
    }

    public String getParentIdentifier() {
        return parentIdentifier;
    }

    public void setParentIdentifier(String parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getCreatedOn() {
        return CreatedOn;
    }
    //endregion

    private boolean hasOwner() {
        return !(this.owner == null || this.owner.isEmpty());
    }

    public void save(){
        DatabaseReference dbRef;
        if (BuildConfig.DEBUG) {
            dbRef = FireBaseHandler.getReference().child("DEBUG").child(getFireBaseEntityName());
        } else {
            dbRef = FireBaseHandler.getReference().child(getFireBaseEntityName());
        }
        if (!hasOwner() && ownerIsCurrentUser()) {
            owner = FireBaseHandler.getUserId();
        }
        String exerciseKey;
        if (getOwnIdentifier() == null) {
            exerciseKey = dbRef.push().getKey();
        } else {
            exerciseKey = getOwnIdentifier();
        }
        if (exerciseKey != null) {
            dbRef.child(exerciseKey).setValue(this);
        }
    }
}