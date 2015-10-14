package com.codepath.googleimagesearch.models.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    private SearchResponseData responseData;
    private int responseStatus;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public SearchResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(SearchResponseData responseData) {
        this.responseData = responseData;
    }
}
