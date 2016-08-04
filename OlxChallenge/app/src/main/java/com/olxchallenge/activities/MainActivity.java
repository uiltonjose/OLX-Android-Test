package com.olxchallenge.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.olxchallenge.R;
import com.olxchallenge.adapters.TabLayoutViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.map_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.list_tab));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayoutViewPagerAdapter adapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectPage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void selectPage(int pageIndex) {
        tabLayout.setScrollPosition(pageIndex, 0f, true);
        viewPager.setCurrentItem(pageIndex);
    }
}
