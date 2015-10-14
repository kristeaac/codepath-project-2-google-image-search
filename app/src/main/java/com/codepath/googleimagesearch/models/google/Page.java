package com.codepath.googleimagesearch.models.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    @JsonProperty("start")
    private String page;
    private int label;

    public int getPage() {
        return page == null ? 0 : Integer.parseInt(page);
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
