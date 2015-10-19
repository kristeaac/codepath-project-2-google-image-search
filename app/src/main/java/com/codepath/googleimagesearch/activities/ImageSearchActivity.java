package com.codepath.googleimagesearch.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.codepath.googleimagesearch.R;
import com.codepath.googleimagesearch.adapters.ImageAdapter;
import com.codepath.googleimagesearch.constants.ExtraKeys;
import com.codepath.googleimagesearch.fragments.FiltersFragment;
import com.codepath.googleimagesearch.helpers.FiltersHelper;
import com.codepath.googleimagesearch.listeners.EndlessScrollListener;
import com.codepath.googleimagesearch.models.google.Image;
import com.codepath.googleimagesearch.helpers.GoogleImageSearchHelper;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

public class ImageSearchActivity extends AppCompatActivity {
    private StaggeredGridView gvResults;
    private List<Image> images;
    private ImageAdapter aImage;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setupViews();
        images = new ArrayList<>();
        aImage = new ImageAdapter(this, images);
        gvResults.setAdapter(aImage);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                GoogleImageSearchHelper.search(query, page, new GoogleImageSearchHelper.ResponseHandler<List<Image>>() {
                    @Override
                    public void onSuccess(List<Image> images) {
                        aImage.addAll(images);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("IMAGE_SEARCH", String.format("Failed to retrieve images, query=[%s]", query), throwable);
                    }
                }, FiltersHelper.loadFilters(getApplicationContext()));
                return true;
            }
        });
    }

    private void setupViews() {
        gvResults = (StaggeredGridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ImageSearchActivity.this, ImageDisplayActivity.class);
                intent.putExtra(ExtraKeys.IMAGE, aImage.getItem(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image_search, menu);
        setupSearchMenuItem(menu);
        setupFiltersMenuItem(menu);
        return true;

    }

    private void setupSearchMenuItem(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                ImageSearchActivity.this.query = query;
                GoogleImageSearchHelper.search(query, new GoogleImageSearchHelper.ResponseHandler<List<Image>>() {
                    @Override
                    public void onSuccess(List<Image> images) {
                        // TODO handle no results case
                        ImageSearchActivity.this.images.clear();
                        aImage.addAll(images);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("IMAGE_SEARCH", String.format("Failed to retrieve images, query=[%s]", query), throwable);
                    }
                }, FiltersHelper.loadFilters(getApplicationContext()));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setupFiltersMenuItem(Menu menu) {
        MenuItem filtersItem = menu.findItem(R.id.action_filters);
        filtersItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showFiltersFragment();
                return false;
            }
        });
    }

    private void showFiltersFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FiltersFragment filtersFragment = new FiltersFragment();
        filtersFragment.show(fm, "fragment_filters");
    }
}
