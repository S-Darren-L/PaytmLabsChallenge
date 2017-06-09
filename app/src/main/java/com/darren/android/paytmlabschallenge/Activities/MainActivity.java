package com.darren.android.paytmlabschallenge.Activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.darren.android.paytmlabschallenge.Adapters.CurrencyAdapter;
import com.darren.android.paytmlabschallenge.Base.App;
import com.darren.android.paytmlabschallenge.Models.CurrencyModel;
import com.darren.android.paytmlabschallenge.Network.ApiRequests;
import com.darren.android.paytmlabschallenge.Network.GsonGetRequest;
import com.darren.android.paytmlabschallenge.R;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String sTAG = "Tag";
    private static final String DEBUG = "Debug";

    @BindView(R.id.currencySelectorSpinner)
    Spinner currencySelectorSpinner;
    @BindView(R.id.inputValueEditText)
    EditText inputValueEditText;
    @BindView(R.id.currenciesRecyclerView)
    RecyclerView currenciesRecyclerView;

    private String selectedCurrency;
    private double inputValue;
    private ArrayList ratesArray;

    private CurrencyAdapter currencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initUI();
        performRequest();
    }

    private void initUI() {
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
        List<String> currencyCodesArray = getAllCurrencies();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyCodesArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySelectorSpinner.setAdapter(spinnerArrayAdapter);
        int spinnerPosition = currencyCodesArray.indexOf(localeCurrency.getCurrencyCode());
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
                String inputValueString = inputValueEditText.getText().toString();
                if(!inputValueString.isEmpty() && inputValueString != null)
                    inputValue = Double.parseDouble(inputValueString);
            }
        });

        ratesArray =  new ArrayList();
        currencyAdapter = new CurrencyAdapter(ratesArray);

        GridLayoutManager recyclerLayoutManager = new GridLayoutManager(getApplicationContext(), 6, LinearLayoutManager.HORIZONTAL,false);

        currenciesRecyclerView.setHasFixedSize(true);
        currenciesRecyclerView.setLayoutManager(recyclerLayoutManager);
        currenciesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        currenciesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        currenciesRecyclerView.setAdapter(currencyAdapter);
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

    private void performRequest() {
        final GsonGetRequest<CurrencyModel> gsonGetRequest =
                ApiRequests.geCurrencyObject
                        (
                                new Response.Listener<CurrencyModel>() {
                                    @Override
                                    public void onResponse(CurrencyModel CurrencyObject) {
                                        onApiResponse(CurrencyObject);
                                    }
                                }
                                ,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Deal with the error here
                                        onApiError(error);
                                    }
                                }
                        );

        App.addRequest(gsonGetRequest, sTAG);
    }

    private void onApiResponse(@NonNull
                               final CurrencyModel CurrencyObject) {
        setData(CurrencyObject);
    }

    private void onApiError(VolleyError error) {
        String json;
        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            json = new String(response.data);
            if(json != null)
                Log.d(DEBUG, json);
        } else if (error != null) {
            Log.d(DEBUG, error.toString());
        }
    }

    private void setData(@NonNull
                         final CurrencyModel CurrencyObject) {

        if(CurrencyObject.getRatesMap() != null)
            ratesArray.addAll(CurrencyObject.getRatesMap().entrySet());
    }

    @Override
    protected void onStop() {
        App.cancelAllRequests(sTAG);

        super.onStop();
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            selectedCurrency = adapterView.getItemAtPosition(position).toString();
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
