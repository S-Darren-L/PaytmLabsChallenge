package com.darren.android.paytmlabschallenge.Models;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Darren on 6/9/2017.
 */

public class CurrencyModelDeserializer implements JsonDeserializer<CurrencyModel> {
    @Override
    public CurrencyModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final CurrencyModel currencyObject = new CurrencyModel();
        final JsonObject jsonObject = json.getAsJsonObject();

        currencyObject.setBase(jsonObject.get("base").getAsString());
        currencyObject.setDate(jsonObject.get("date").getAsString());
        JsonObject ratesObject = jsonObject.get("rates").getAsJsonObject();
        currencyObject.setRates(ratesObject);

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Double>>(){}.getType();
        Map<String, Double> ratesMap = gson.fromJson(ratesObject, type);
        currencyObject.setRatesMap(ratesMap);

        return currencyObject;
    }
}
