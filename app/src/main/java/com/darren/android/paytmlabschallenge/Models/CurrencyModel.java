package com.darren.android.paytmlabschallenge.Models;

import com.darren.android.paytmlabschallenge.Models.IModels.ICurrencyModel;

/**
 * Created by Darren on 6/8/2017.
 */

public class CurrencyModel implements ICurrencyModel {

    private String currencyCode, currencyValue;

    public CurrencyModel() {
    }

    public CurrencyModel(String currencyCode, String currencyValue) {
        this.currencyCode = currencyCode;
        this.currencyValue = currencyValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }
}
