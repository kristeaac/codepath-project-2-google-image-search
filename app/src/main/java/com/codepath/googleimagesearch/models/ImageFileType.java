package com.codepath.googleimagesearch.models;

public enum ImageFileType {
    ANY("any"), JPG("jpg"), PNG("png"), GIF("gif"), BMP("bmp");

    private String displayName;

    ImageFileType(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public static ImageFileType fromDisplayName(String displayName) {
        for (ImageFileType fileType : values()) {
            if (fileType.displayName.equals(displayName)) {
                return fileType;
            }
        }
        return null;
    }
}
