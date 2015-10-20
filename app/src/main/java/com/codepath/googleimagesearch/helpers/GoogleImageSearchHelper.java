package com.codepath.googleimagesearch.helpers;


import com.codepath.googleimagesearch.models.Filters;
import com.codepath.googleimagesearch.models.ImageFileType;
import com.codepath.googleimagesearch.models.ImageSize;
import com.codepath.googleimagesearch.models.google.Image;
import com.codepath.googleimagesearch.models.google.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

        if (!ImageSize.ANY.equals(ObjectUtils.defaultIfNull(filters.getImageSize(), ImageSize.ANY))) {
            url += "&imgsz=" + getImageSizeQueryParam(filters.getImageSize());
        }

        if (StringUtils.isNotBlank(filters.getSiteFilter())) {
            url += "&as_sitesearch=" + filters.getSiteFilter();
        }

        if (!ImageFileType.ANY.equals(ObjectUtils.defaultIfNull(filters.getImageFileType(), ImageFileType.ANY))) {
            url += "&as_filetype=" + getImageFileTypeQueryParam(filters.getImageFileType());
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

    private static String getImageFileTypeQueryParam(ImageFileType imageFileType) {
        switch (imageFileType) {
            case JPG:
                return "jpg";
            case PNG:
                return "png";
            case BMP:
                return "bmp";
            case GIF:
                return "gif";
            case ANY:
            default:
                return "";
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
