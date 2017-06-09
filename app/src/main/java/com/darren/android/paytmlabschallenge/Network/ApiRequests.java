package com.darren.android.paytmlabschallenge.Network;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.darren.android.paytmlabschallenge.Models.CurrencyModel;
import com.darren.android.paytmlabschallenge.Models.CurrencyModelDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Darren on 6/8/2017.
 */

public class ApiRequests {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(CurrencyModel.class, new CurrencyModelDeserializer())
            .create();

    public static GsonGetRequest<CurrencyModel> geCurrencyObject
            (
                    @NonNull
                    final Response.Listener<CurrencyModel> listener,
                    @NonNull
                    final Response.ErrorListener errorListener
            ) {
        final String url = BuildConfig.apiDomainName + "/latest";

        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<CurrencyModel>() {
                        }.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }

//    public static GsonGetRequest<ArrayList<CurrencyModel>> geCurrencyObjectArray
//            (
//                    @NonNull
//                    final Response.Listener<ArrayList<CurrencyModel>> listener,
//                    @NonNull
//                    final Response.ErrorListener errorListener
//            ) {
//        final String url = BuildConfig.apiDomainName + "/latest";
//
//        return new GsonGetRequest<>
//                (
//                        url,
//                        new TypeToken<ArrayList<CurrencyModel>>() {
//                        }.getType(),
//                        gson,
//                        listener,
//                        errorListener
//                );
//    }

}
