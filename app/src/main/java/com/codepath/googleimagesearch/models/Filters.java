package com.codepath.googleimagesearch.models;

public class Filters {
    private ImageSize imageSize;
    private ImageColor imageColor;
    private ImageType imageType;
    private String siteFilter;
    private ImageFileType imageFileType;

    public Filters(ImageSize imageSize, ImageColor imageColor, ImageType imageType, String siteFilter, ImageFileType imageFileType) {
        this.imageSize = imageSize;
        this.imageColor = imageColor;
        this.imageType = imageType;
        this.siteFilter = siteFilter;
        this.imageFileType = imageFileType;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public ImageColor getImageColor() {
        return imageColor;
    }

    public void setImageColor(ImageColor imageColor) {
        this.imageColor = imageColor;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
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
