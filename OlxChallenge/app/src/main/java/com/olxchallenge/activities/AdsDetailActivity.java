package com.olxchallenge.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olxchallenge.R;
import com.olxchallenge.bean.Ads;
import com.olxchallenge.util.AndroidUtil;

public class AdsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_detail_activity);

        Ads ads = (Ads) getIntent().getExtras().getSerializable("ads");
        initViews(ads);
    }

    private void initViews(Ads ads) {

        View closeBtn = findViewById(R.id.close_iv);
        if (closeBtn != null) {
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        ImageView coverIv = (ImageView) findViewById(R.id.cover_iv);
        String coverImage = AndroidUtil.getImageUrl(ads);
        if (coverImage != null && !coverImage.isEmpty() && coverIv != null) {
            Glide.with(this).load(coverImage)
                    .centerCrop()
                    .into(coverIv);
        }

        TextView titleTv = (TextView) findViewById(R.id.title_tv);
        if (titleTv != null) {
            titleTv.setText(ads.getTitle());
        }

        TextView priceTv = (TextView) findViewById(R.id.price_tv);
        if (priceTv != null) {
            priceTv.setText(ads.getListLabelAd());
        }

        TextView descriptionTv = (TextView) findViewById(R.id.description_tv);
        if (descriptionTv != null) {
            descriptionTv.setText(ads.getDescription());
        }
    }
}
