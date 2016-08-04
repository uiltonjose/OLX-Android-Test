package com.olxchallenge.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olxchallenge.R;
import com.olxchallenge.adapters.AdsAdapter;
import com.olxchallenge.bean.Page;
import com.olxchallenge.comm.FetchServerTask;
import com.olxchallenge.comm.FetchServerTask.ServerCallback;
import com.olxchallenge.components.GeneralSwipeRefreshLayout;
import com.olxchallenge.util.AndroidUtil;

public class ListAdsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ServerCallback {

    private static final int VERTICAL_ORIENTATION = 1;
    private static final int COLUMNS_GRID = 2;

    private SwipeRefreshLayout swipeRefreshLayout;
    private AdsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_ads_fragments, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMNS_GRID, VERTICAL_ORIENTATION));

        adapter = new AdsAdapter(getContext());
        recyclerView.swapAdapter(adapter, false);

        swipeRefreshLayout = (GeneralSwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (AndroidUtil.hasConnectivity(getContext())) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    new FetchServerTask(ListAdsFragment.this).execute();
                }
            });
        } else {
            Snackbar.make(swipeRefreshLayout, R.string.no_connectivity, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefresh() {

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                new FetchServerTask(ListAdsFragment.this).execute();
            }
            //});
        }, 1500); //just to see animation :D
    }

    @Override
    public void resultServer(Page page) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setItems(page.getAdsList());
    }
}
