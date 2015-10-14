package com.codepath.googleimagesearch.helpers;


import android.content.Context;
import android.content.SharedPreferences;

import com.codepath.googleimagesearch.models.Filters;
import com.codepath.googleimagesearch.models.ImageColor;
import com.codepath.googleimagesearch.models.ImageSize;
import com.codepath.googleimagesearch.models.ImageType;

public class FiltersHelper {
    private static final String PREFS_NAME = "GOOGLE_IMAGE_SEARCH_FILTERS";

    public static Filters loadFilters(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        ImageSize imageSize = ImageSize.valueOf(settings.getString("imageSize", null));
        ImageColor colorFilter = ImageColor.valueOf(settings.getString("colorFilter", null));
        ImageType imageType = ImageType.valueOf(settings.getString("imageType", null));
        String siteFilter = settings.getString("siteFilter", null);
        return new Filters(imageSize, colorFilter, imageType, siteFilter);
    }

    public static void saveFilters(Context context, Filters filters) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imageSize", filters.getImageSize().name());
        editor.putString("colorFilter", filters.getImageColor().name());
        editor.putString("imageType", filters.getImageType().name());
        editor.putString("siteFilter", filters.getSiteFilter());
        editor.commit();
    }
}
