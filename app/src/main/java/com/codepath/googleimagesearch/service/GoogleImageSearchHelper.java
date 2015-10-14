package com.codepath.googleimagesearch.service;


import com.codepath.googleimagesearch.model.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GoogleImageSearchHelper {

    private GoogleImageSearchHelper() {

    }

    public static void search(String query, final ResponseHandler<SearchResponse> responseHandler) {
        String url = String.format("https://ajax.googleapis.com/ajax/services/search/images?q=%s&v=1.0", query);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();

                try {
                    SearchResponse searchResponse = mapper.readValue(response.toString(), SearchResponse.class);
                    responseHandler.onSuccess(searchResponse);

                } catch (Exception e) {
                    responseHandler.onFailure(e);
                }
            }
        });
    }

    public static interface ResponseHandler<TYPE> {

        void onSuccess(TYPE type);

        void onFailure(Throwable throwable);

    }

}
