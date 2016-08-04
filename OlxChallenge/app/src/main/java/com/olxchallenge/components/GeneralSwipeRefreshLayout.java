package com.olxchallenge.components;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

public class GeneralSwipeRefreshLayout extends SwipeRefreshLayout {

    public GeneralSwipeRefreshLayout(Context context) {
        super(context);
    }

    public GeneralSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    View mMyScrollableView = null;

    public void setMyScrollableView(View view) {
        mMyScrollableView = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mMyScrollableView == null)
            return false;
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mMyScrollableView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mMyScrollableView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return mMyScrollableView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mMyScrollableView, -1);
        }
    }
}
