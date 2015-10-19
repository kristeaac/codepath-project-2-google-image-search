package com.codepath.googleimagesearch.helpers;


import android.content.Context;
import android.content.SharedPreferences;

import com.codepath.googleimagesearch.models.Filters;
import com.codepath.googleimagesearch.models.ImageColor;
import com.codepath.googleimagesearch.models.ImageFileType;
import com.codepath.googleimagesearch.models.ImageSize;
import com.codepath.googleimagesearch.models.ImageType;

public class FiltersHelper {
    private static final String PREFS_NAME = "GOOGLE_IMAGE_SEARCH_FILTERS";

    public static Filters loadFilters(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        ImageSize imageSize = ImageSize.valueOf(settings.getString("imageSize", ImageSize.ANY.name()));
        ImageColor colorFilter = ImageColor.valueOf(settings.getString("colorFilter", ImageColor.ANY.name()));
        ImageType imageType = ImageType.valueOf(settings.getString("imageType", ImageType.ANY.name()));
        ImageFileType fileType = ImageFileType.valueOf(settings.getString("imageFileType", ImageFileType.ANY.name()));
        String siteFilter = settings.getString("siteFilter", null);
        return new Filters(imageSize, colorFilter, imageType, siteFilter, fileType);
    }

    public static void saveFilters(Context context, Filters filters) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imageSize", filters.getImageSize().name());
        editor.putString("colorFilter", filters.getImageColor().name());
        editor.putString("imageType", filters.getImageType().name());
        editor.putString("siteFilter", filters.getSiteFilter());
        editor.putString("imageFileType", filters.getImageFileType().name());
        editor.commit();
    }
}
