package com.codepath.googleimagesearch.helpers;


import com.codepath.googleimagesearch.models.Filters;
import com.codepath.googleimagesearch.models.ImageColor;
import com.codepath.googleimagesearch.models.ImageSize;
import com.codepath.googleimagesearch.models.google.Image;
import com.codepath.googleimagesearch.models.google.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GoogleImageSearchHelper {

    private GoogleImageSearchHelper() {

    }

    public static void search(String query, final ResponseHandler<List<Image>> responseHandler, Filters filters) {
        search(query, 0, responseHandler, filters);
    }

    public static void search(final String query, final int page, final ResponseHandler<List<Image>> responseHandler, Filters filters) {
        int offset = page * 8;
        searchWithOffset(query, offset, responseHandler, filters);
    }

    private static void searchWithOffset(String query, final int offset, final ResponseHandler<List<Image>> responseHandler, Filters filters) {
        String url = String.format("https://ajax.googleapis.com/ajax/services/search/images?q=%s&v=1.0&rsz=8&start=%s", query, offset);

        if (!ImageColor.ANY.equals(ObjectUtils.defaultIfNull(filters.getImageColor(), ImageColor.ANY))) {
            url += "&imgcolor=" + getImageColorQueryParam(filters.getImageColor());
        }

        if (!ImageSize.ANY.equals(ObjectUtils.defaultIfNull(filters.getImageSize(), ImageSize.ANY))) {
            url += "&imgsz=" + getImageSizeQueryParam(filters.getImageSize());
        }

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

    private static String getImageColorQueryParam(ImageColor imageColor) {
        if (imageColor == null || imageColor.equals(ImageColor.ANY)) {
            return "";
        } else {
            return imageColor.name().toLowerCase();
        }
    }

    private static String getImageSizeQueryParam(ImageSize imageSize) {
        switch (imageSize) {
            case SMALL:
                return "icon";
            case MEDIUM:
                return "medium";
            case LARGE:
                return "xxlarge";
            case EXTRA_LARGE:
                return "huge";
            default:
            case ANY:
                return "";
        }
    }

    public static interface ResponseHandler<TYPE> {

        void onSuccess(TYPE type);

        void onFailure(Throwable throwable);

    }

}
