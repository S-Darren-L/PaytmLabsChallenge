package com.darren.android.paytmlabschallenge.Models;

import com.darren.android.paytmlabschallenge.Models.IModels.ICurrencyModel;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Created by Darren on 6/8/2017.
 */

public class CurrencyModel implements ICurrencyModel {

    private String base, date;
    private JsonObject rates;
    private Map<String, Double> ratesMap;

    public CurrencyModel() {
    }

    public CurrencyModel(String base, String date, JsonObject rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase (String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {  this.date = date; }

    public JsonObject getRates() {
        return rates;
    }

    public void setRates(JsonObject rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRatesMap() {
        return ratesMap;
    }

    public void setRatesMap(Map<String, Double> ratesMap) {
        this.ratesMap = ratesMap;
    }
}
