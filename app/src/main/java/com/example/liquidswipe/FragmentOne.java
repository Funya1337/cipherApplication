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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class FragmentOne extends Fragment {

    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout phraseInput;
    TextInputLayout keyInput;
    AutoCompleteTextView alfInput;
    TextView resView;
    String result = "";
    Button encryptBtn;
    Button decryptBtn;
    int newKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        encryptBtn = rootView.findViewById(R.id.encryptBtn);
        decryptBtn = rootView.findViewById(R.id.decryptBtn);
        encryptBtn.setOnClickListener(v -> {
            phraseInput = rootView.findViewById(R.id.phraseField);
            keyInput = rootView.findViewById(R.id.keyField);
            alfInput = rootView.findViewById(R.id.dropdown_menu);
            resView = rootView.findViewById(R.id.textView_res);
            String message = phraseInput.getEditText().getText().toString();
            String key = keyInput.getEditText().getText().toString();
            String lang = alfInput.getText().toString();

            if (checkKey(key) || key.equals("")) {
                if (key.equals(""))
                    newKey = 0;
                else
                    newKey = Integer.parseInt(key);

                if (lang.equals("English"))
                    result = encrypt(message, newKey, "abcdefghijklmnopqrstuvwxyz");
                if (lang.equals("Russian"))
                    result = encrypt(message, newKey, "абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
                else
                    result = encrypt(message, newKey, "abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя");
                resView.setText(result);
            } else {
                Toast.makeText(getContext(), "Details Saved Successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        decryptBtn.setOnClickListener(v -> {
            phraseInput = rootView.findViewById(R.id.phraseField);
            keyInput = rootView.findViewById(R.id.keyField);
            alfInput = rootView.findViewById(R.id.dropdown_menu);
            resView = rootView.findViewById(R.id.textView_res);
            String message = phraseInput.getEditText().getText().toString();
            String key = keyInput.getEditText().getText().toString();
            String lang = alfInput.getText().toString();

            if (checkKey(key) || key.equals("")) {
                if (key.equals(""))
                    newKey = 0;
                else
                    newKey = Integer.parseInt(key);

                if (lang.equals("English"))
                    result = decrypt(message, newKey, "abcdefghijklmnopqrstuvwxyz");
                if (lang.equals("Russian"))
                    result = decrypt(message, newKey, "абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
                else
                    result = decrypt(message, newKey, "abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя");
                resView.setText(result);
            } else {
                Toast.makeText(getContext(), "Details Saved Successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        createDropDownElements(rootView, autoCompleteTextView);
        return rootView;
    }

    public Boolean checkKey(String key) {
        int cnt = 0;
        for (int i = 0; i < key.length(); i++) {
            if (Character.isDigit(key.charAt(i)))
                cnt += 1;
        }
        return cnt == key.length();
    }

    public String encrypt(String message, Integer key, String alf) {
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int charPosition = alf.indexOf(message.charAt(i));
            int keyval = (key + charPosition) % 26;
            char replaceVAL = alf.charAt(keyval);
            cipherText.append(replaceVAL);
        }
        return cipherText.toString();
    }

    public String decrypt(String cipherText, int key, String alf) {
        cipherText = cipherText.toLowerCase();
        String message = "";
        for (int ii = 0; ii < cipherText.length(); ii++) {
            int charPosition = alf.indexOf(cipherText.charAt(ii));
            int keyVal = (charPosition - key) % 26;
            if (keyVal < 0) {
                keyVal = alf.length() + keyVal;
            }
            char replaceVal = alf.charAt(keyVal);
            message += replaceVal;
        }
        return message;
    }

    public void createDropDownElements(View rootView, AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView = rootView.findViewById(R.id.dropdown_menu);

        String []option = {"English", "Russian", "Mixed"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.option_item, option);

        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}