//package com.darren.android.paytmlabschallenge.Models;
//
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//
//import java.lang.reflect.Type;
//
///**
// * Created by Darren on 6/9/2017.
// */
//
//public class RateModelDeserializer implements JsonDeserializer<RatesModel> {
//    @Override
//    public RatesModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//            throws JsonParseException {
//        final RatesModel ratesObject = new RatesModel();
//        final JsonObject jsonObject = json.getAsJsonObject();
//
////        ratesObject.setCurrencyCode(jsonObject.get("base").getAsString());
////        ratesObject.setCurrencyValue(jsonObject.get("date").getAsDouble());
//
//        return ratesObject;
//    }
//}