package com.codepath.googleimagesearch.models;

public enum  ImageSize {
    SMALL("small"), MEDIUM("medium"), LARGE("large"), EXTRA_LARGE("extra-large");

    private String displayName;

    ImageSize(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public static ImageSize fromDisplayName(String displayName) {
        for (ImageSize imageSize : values()) {
            if (imageSize.displayName.equals(displayName)) {
                return imageSize;
            }
        }
        return null;
    }
}
