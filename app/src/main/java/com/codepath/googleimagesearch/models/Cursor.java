package com.codepath.googleimagesearch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cursor {
    private String resultCount;
    private List<Page> pages;
    private int currentPageIndex;

    public int getResultCount() {
        return resultCount == null ? 0 : Integer.parseInt(resultCount);
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public List<Page> getPages() {
        return pages == null ? Collections.<Page>emptyList() : pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }
}
