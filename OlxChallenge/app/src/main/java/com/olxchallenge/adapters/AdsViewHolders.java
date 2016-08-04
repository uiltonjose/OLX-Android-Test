package com.olxchallenge.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olxchallenge.R;
import com.olxchallenge.activities.AdsDetailActivity;
import com.olxchallenge.bean.Ads;

public class AdsViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageView;
    public TextView description;
    public TextView price;
    private Context context;
    private AdsAdapter adapter;


    public AdsViewHolders(Context context, AdsAdapter adapter, View itemView) {
        super(itemView);
        this.context = context;
        this.adapter = adapter;

        itemView.setOnClickListener(this);
        imageView = (ImageView) itemView.findViewById(R.id.ads_cover);
        description = (TextView) itemView.findViewById(R.id.description);
        price = (TextView) itemView.findViewById(R.id.price);
    }

    @Override
    public void onClick(View v) {

        Ads item = adapter.items.get(getAdapterPosition());
        Intent intent = new Intent(context, AdsDetailActivity.class);
        intent.putExtra("ads", item);
        context.startActivity(intent);
    }
}
