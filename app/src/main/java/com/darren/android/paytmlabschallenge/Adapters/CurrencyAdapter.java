package com.darren.android.paytmlabschallenge.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darren.android.paytmlabschallenge.Models.CurrencyModel;
import com.darren.android.paytmlabschallenge.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darren on 6/8/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>{
    private List<CurrencyModel> currenciesList;

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.currencyCodeTextView)
        TextView currencyCodeTextView;
        @BindView(R.id.currencyValueTextView)
        TextView currencyValueTextView;

        public CurrencyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public CurrencyAdapter(List<CurrencyModel> currenciesList) {
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
        CurrencyModel currencyItem = currenciesList.get(position);
        holder.currencyCodeTextView.setText(currencyItem.getCurrencyCode());
        holder.currencyValueTextView.setText(currencyItem.getCurrencyValue());
    }

    @Override
    public int getItemCount() {
        return currenciesList.size();
    }
}
