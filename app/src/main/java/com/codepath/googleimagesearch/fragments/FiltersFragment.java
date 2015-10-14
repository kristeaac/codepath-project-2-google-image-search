package com.codepath.googleimagesearch.fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codepath.googleimagesearch.R;

public class FiltersFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container);
        getDialog().setTitle("Advanced Search Options");

        // image sizes
        Spinner spnImageSizes = (Spinner) view.findViewById(R.id.spnImageSizes);
        ArrayAdapter<CharSequence> aImageSizes = ArrayAdapter.createFromResource(getContext(),
                R.array.image_sizes, android.R.layout.simple_spinner_item);
        aImageSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImageSizes.setAdapter(aImageSizes);

        // color filters
        Spinner spnColorFilters = (Spinner) view.findViewById(R.id.spnColorFilters);
        ArrayAdapter<CharSequence> aColorFilters = ArrayAdapter.createFromResource(getContext(),
                R.array.color_filters, android.R.layout.simple_spinner_item);
        aColorFilters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColorFilters.setAdapter(aColorFilters);

        // image types
        Spinner spnImageTypes = (Spinner) view.findViewById(R.id.spnImageTypes);
        ArrayAdapter<CharSequence> aImageTypes = ArrayAdapter.createFromResource(getContext(),
                R.array.image_types, android.R.layout.simple_spinner_item);
        aImageTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImageTypes.setAdapter(aImageTypes);

        return view;
    }
}
