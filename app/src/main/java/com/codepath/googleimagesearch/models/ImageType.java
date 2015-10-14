package com.codepath.googleimagesearch.models;


public enum  ImageType {
    FACES("faces"), PHOTO("photo"), CLIP_ART("clip art"), LINE_ART("line art");

    private String displayName;

    ImageType(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public static ImageType fromDisplayName(String displayName) {
        for (ImageType imageType : values()) {
            if (imageType.displayName.equals(displayName)) {
                return imageType;
            }
        }
        return null;
    }
}
