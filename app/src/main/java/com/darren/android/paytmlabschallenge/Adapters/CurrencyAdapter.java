package com.darren.android.paytmlabschallenge.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darren.android.paytmlabschallenge.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Darren on 6/8/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>{
    private ArrayList currenciesList;
    public TextView codeTextView, valueTextView;

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        public CurrencyViewHolder(View view) {
            super(view);

            codeTextView = (TextView) view.findViewById(R.id.currencyCodeTextView);
            valueTextView = (TextView) view.findViewById(R.id.currencyValueTextView);
        }
    }


    public CurrencyAdapter(ArrayList currenciesList) {
        this.currenciesList = currenciesList;

    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_currency_item, parent, false);

        return new CurrencyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Map.Entry<String, Double> item = getItem(position);
        codeTextView.setText(item.getKey());
        valueTextView.setText(item.getValue().toString());
    }

    public Map.Entry<String, Double> getItem(int position) {
        return (Map.Entry) currenciesList.get(position);
    }

    @Override
    public int getItemCount() {
        return currenciesList.size();
    }
}
