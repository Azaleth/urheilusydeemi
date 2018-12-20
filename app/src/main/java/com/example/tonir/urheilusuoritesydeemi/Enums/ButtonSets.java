package com.example.tonir.urheilusuoritesydeemi.Enums;

public enum ButtonSets {
    SMALL,
    MEDIUM,
    LARGE;

    public String[] GetButtonTexts(boolean positive){
        switch (this){
            case SMALL:
                if(positive) {
                    return new String[]{
                            "+1",
                            "+1.25",
                            "+2.5",
                            "+5"
                    };
                }else{
                    return new String[]{
                            "-1",
                            "-1.25",
                            "-2.5",
                            "-5"
                    };
                }
            case MEDIUM:
                if(positive) {
                    return new String[]{
                            "+2.5",
                            "+5",
                            "+10",
                            "+15"
                    };
                }else{
                    return new String[]{
                            "-2.5",
                            "-5",
                            "-10",
                            "-15"
                    };
                }
            case LARGE
                    :if(positive) {
                return new String[]{
                        "+1.25",
                        "+10",
                        "+15",
                        "+20"
                };
            }else{
                return new String[]{
                        "-1.25",
                        "-10",
                        "-15",
                        "-20"
                };
            }
            default:
                return new String[] {"NULL"};
        }
    }
}
