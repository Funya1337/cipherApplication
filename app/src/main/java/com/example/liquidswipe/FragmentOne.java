package com.example.liquidswipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class FragmentOne extends Fragment {

    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout phraseInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        createDropDownElements(rootView, autoCompleteTextView);

        return rootView;
    }

    public void createDropDownElements(View rootView, AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView = rootView.findViewById(R.id.dropdown_menu);

        String []option = {"English", "Russian", "Mixed"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.option_item, option);

        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}