package com.example.recycleritemanimator.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 竖直的RecyclerView间距
 */
public class VerticalItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = mSpace;
    }

    public VerticalItemDecoration(int space) {
        mSpace = space;
    }

}

