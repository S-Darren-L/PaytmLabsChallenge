package com.darren.android.paytmlabschallenge.Models.IModels;

import com.google.gson.JsonObject;

import java.util.TreeMap;

/**
 * Created by Darren on 6/8/2017.
 */

public interface ICurrencyModel {
    public String getBase();

    public void setBase (String base);

    public String getDate();

    public void setDate(String currencyValue);

    public JsonObject getRates();

    public void setRates(JsonObject rates);

    public TreeMap<String, Double> getRatesMap();

    public void setRatesMap(TreeMap<String, Double> ratesMap);
}
