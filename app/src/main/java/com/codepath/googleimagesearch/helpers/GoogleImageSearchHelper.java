package com.codepath.googleimagesearch.helpers;


import com.codepath.googleimagesearch.models.Image;
import com.codepath.googleimagesearch.models.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GoogleImageSearchHelper {

    private GoogleImageSearchHelper() {

    }

    public static void search(String query, final ResponseHandler<List<Image>> responseHandler) {
        search(query, 0, responseHandler);
    }

    public static void search(final String query, final int page, final ResponseHandler<List<Image>> responseHandler) {
        int offset = page * 8;
        searchWithOffset(query, offset, responseHandler);
    }

    private static void searchWithOffset(String query, final int offset, final ResponseHandler<List<Image>> responseHandler) {
        String url = String.format("https://ajax.googleapis.com/ajax/services/search/images?q=%s&v=1.0&rsz=8&start=%s", query, offset);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    SearchResponse searchResponse = mapper.readValue(response.toString(), SearchResponse.class);
                    responseHandler.onSuccess(searchResponse.getResponseData().getResults());
                } catch (Exception e) {
                    responseHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(throwable);
            }
        });
    }

    public static interface ResponseHandler<TYPE> {

        void onSuccess(TYPE type);

        void onFailure(Throwable throwable);

    }

}
