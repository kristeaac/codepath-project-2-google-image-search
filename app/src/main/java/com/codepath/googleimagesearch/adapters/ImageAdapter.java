package com.codepath.googleimagesearch.adapters;


import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.googleimagesearch.R;
import com.codepath.googleimagesearch.models.google.Image;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<Image> {

    public ImageAdapter(Context context, List<Image> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Image image = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        DynamicHeightImageView ivThumbnail = (DynamicHeightImageView) convertView.findViewById(R.id.ivThumbnail);
        ivThumbnail.setImageResource(0);
        ivThumbnail.setHeightRatio(1.0);
        Picasso.with(getContext()).load(image.getThumbnailUrl()).into(ivThumbnail);
        tvTitle.setText(Html.fromHtml(image.getTitle()));
        return convertView;
    }
}
