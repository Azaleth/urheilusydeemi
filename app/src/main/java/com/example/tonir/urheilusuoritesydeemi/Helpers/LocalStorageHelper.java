package com.example.tonir.urheilusuoritesydeemi.Helpers;

public class LocalStorageHelper {

    public static String GetLastValueByExerciseType(String exerciseType, String valueType) {
        //TODO

        switch (valueType) {
            case "weights":
                return "100" + " kg";
            case "repeats":
                return "12";
            default:
                return "404";
        }
    }
}
