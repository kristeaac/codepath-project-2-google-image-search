package com.codepath.googleimagesearch.fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.googleimagesearch.R;
import com.codepath.googleimagesearch.helpers.FiltersHelper;
import com.codepath.googleimagesearch.models.Filters;
import com.codepath.googleimagesearch.models.ImageColor;
import com.codepath.googleimagesearch.models.ImageSize;
import com.codepath.googleimagesearch.models.ImageType;

import java.util.ArrayList;
import java.util.List;

public class FiltersFragment extends DialogFragment {
    private Filters filters;
    private EditText etSiteFilter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container);
        getDialog().setTitle(R.string.search_options_title);
        filters = FiltersHelper.loadFilters(getContext());

        List<String> imageSizes = new ArrayList<>(ImageSize.values().length);
        for (ImageSize imageSize : ImageSize.values()) {
            imageSizes.add(imageSize.getDisplayName());
        }

        setupSpinner(view, R.id.spnImageSizes, imageSizes, filters.getImageSize() == null ? 0 : filters.getImageSize().ordinal(), new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filters.setImageSize(ImageSize.fromDisplayName(parent.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filters.setImageSize(null);
            }
        });

        List<String> imageColors = new ArrayList<>(ImageColor.values().length);
        for (ImageColor imageColor : ImageColor.values()) {
            imageColors.add(imageColor.getDisplayName());
        }

        setupSpinner(view, R.id.spnColorFilters, imageColors, filters.getImageColor() == null ? 0 : filters.getImageColor().ordinal(), new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filters.setImageColor(ImageColor.fromDisplayName(parent.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filters.setImageColor(null);
            }
        });

        List<String> imageTypes = new ArrayList<>(ImageType.values().length);
        for (ImageType imageType : ImageType.values()) {
            imageTypes.add(imageType.getDisplayName());
        }

        setupSpinner(view, R.id.spnImageTypes, imageTypes, filters.getImageType() == null ? 0 : filters.getImageType().ordinal(), new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filters.setImageType(ImageType.fromDisplayName(parent.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filters.setImageType(null);
            }
        });

        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filters.setSiteFilter(etSiteFilter.getText().toString());
                FiltersHelper.saveFilters(getContext(), filters);
                dismiss();
            }
        });

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        etSiteFilter = (EditText) view.findViewById(R.id.etSiteFilter);
        etSiteFilter.setText(filters.getSiteFilter());

        return view;
    }

    private void setupSpinner(View view, int spinnerResourceId, List<String> resources, int selectedPosition, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        Spinner spinner = (Spinner) view.findViewById(spinnerResourceId);
        String[] resourceArray = new String[resources.size()];
        for (int i = 0; i < resources.size(); i++) {
            resourceArray[i] = resources.get(i);
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), android.R.layout.simple_spinner_item, resourceArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        spinner.setSelection(selectedPosition);
    }
}