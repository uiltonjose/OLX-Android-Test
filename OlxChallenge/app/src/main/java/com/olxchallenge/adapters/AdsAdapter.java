package com.olxchallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.olxchallenge.R;
import com.olxchallenge.bean.Ads;
import com.olxchallenge.util.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsViewHolders> {

    public List<Ads> items;
    private Context context;

    public AdsAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public AdsViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_item, null);
        return new AdsViewHolders(context, this, layoutView);
    }

    @Override
    public void onBindViewHolder(AdsViewHolders holder, int position) {

        if (position < getItemCount()) {
            Ads item = items.get(position);

            String coverImage = AndroidUtil.getImageUrl(item);
            if (coverImage != null && !coverImage.isEmpty()) {
                Glide.with(context).load(coverImage)
                        .centerCrop()
                        .into(holder.imageView);
                holder.imageView.setVisibility(View.VISIBLE);
            } else {
                holder.imageView.setVisibility(View.GONE);
            }
            holder.imageView.requestLayout();
            String price = item.getListLabelAd();
            if (price == null || !price.isEmpty()) {
                price = item.getPriceNumeric() + "€";
            }
            holder.price.setText(price);
            holder.description.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Ads> items) {
        this.items = items;
    }
}
