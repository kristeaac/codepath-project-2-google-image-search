package com.codepath.googleimagesearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    @JsonProperty("imageId")
    private String id;
    private String url;
    @JsonProperty("tbUrl")
    private String thumbnailUrl;
    private String width;
    private String height;
    @JsonProperty("tbWidth")
    private String thumbnailWidth;
    @JsonProperty("tbHeight")
    private String thumbnailHeight;
    private String visibleUrl;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getWidth() {
        return width == null ? 0 : Integer.parseInt(width);
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int getHeight() {
        return height == null ? 0 : Integer.parseInt(height);
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth == null ? 0 : Integer.parseInt(thumbnailWidth);
    }

    public void setThumbnailWidth(String thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight == null ? 0 : Integer.parseInt(thumbnailHeight);
    }

    public void setThumbnailHeight(String thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
