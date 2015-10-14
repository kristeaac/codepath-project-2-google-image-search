package com.codepath.googleimagesearch.models;

public enum  ImageColor {
    BLACK("black"), BLUE("blue"), BROWN("brown"), GRAY("gray"), GREEN("green");

    private String displayName;

    ImageColor(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public static ImageColor fromDisplayName(String displayName) {
        for (ImageColor imageColor : values()) {
            if (imageColor.displayName.equals(displayName)) {
                return imageColor;
            }
        }
        return null;
    }
}
