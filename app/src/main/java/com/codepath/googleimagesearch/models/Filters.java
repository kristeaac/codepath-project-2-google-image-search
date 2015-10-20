package com.codepath.googleimagesearch.models;

public class Filters {
    private ImageSize imageSize;
    private String siteFilter;
    private ImageFileType imageFileType;

    public Filters(ImageSize imageSize, String siteFilter, ImageFileType imageFileType) {
        this.imageSize = imageSize;
        this.siteFilter = siteFilter;
        this.imageFileType = imageFileType;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public String getSiteFilter() {
        return siteFilter;
    }

    public void setSiteFilter(String siteFilter) {
        this.siteFilter = siteFilter;
    }

    public ImageFileType getImageFileType() {
        return imageFileType;
    }

    public void setImageFileType(ImageFileType imageFileType) {
        this.imageFileType = imageFileType;
    }
}
