package com.darren.android.paytmlabschallenge;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.currencySelectorSpinner)
    Spinner currencySelectorSpinner;
    @BindView(R.id.inputValueEditText)
    EditText inputValueEditText;

    private String SelectedCurrency;
    private double inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Get current Locale
        Locale currentLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentLocale = getResources().getConfiguration().getLocales().get(0);
        } else {
            currentLocale = getResources().getConfiguration().locale;
        }
        Currency localeCurrency = Currency.getInstance(currentLocale);

        // Set spinner adapter, resource, and default value
        List<String> currencyAbbreviationsArray = getAllCurrencies();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyAbbreviationsArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySelectorSpinner.setAdapter(spinnerArrayAdapter);
        int spinnerPosition = currencyAbbreviationsArray.indexOf(localeCurrency.getCurrencyCode());
        currencySelectorSpinner.setSelection(spinnerPosition);
        // Set spinner selected listener
        currencySelectorSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        // Set EditText listener
        inputValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                inputValue = Double.parseDouble(inputValueEditText.getText().toString());
            }
        });
    }

    public static List<String> getAllCurrencies() {
        List<String> toret = new ArrayList<>();
        Locale[] locs = Locale.getAvailableLocales();

        for (Locale loc : locs) {
            try {
                Currency currency = Currency.getInstance(loc);

                if (currency != null) {
                    toret.add(currency.getCurrencyCode());
                }
            } catch (Exception exc) {
                // Locale not found
            }
        }
        return toret;
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            SelectedCurrency = adapterView.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    //Dismiss keyboard when touch outside of EditText
    @Override
    public boolean onTouchEvent(MotionEvent event){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
