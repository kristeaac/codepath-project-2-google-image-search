package com.codepath.googleimagesearch.model;

import android.database.Cursor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseData {
    private List<Image> results;
    private Cursor cursor;

    public List<Image> getResults() {
        return results == null ? Collections.<Image>emptyList() : results;
    }

    public void setResults(List<Image> results) {
        this.results = results;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}
