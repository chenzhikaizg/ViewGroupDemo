package com.example.administrator.viwgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by chenzhikai on 2017/12/30.
 */

public class TabLayout extends ViewGroup {

    private List<List<View>> mChildViews = new ArrayList<>();

    private BaseAdapter mAdapter;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 清空集合
        mChildViews.clear();
        int childCount = getChildCount();
        //获取宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取高度
        int height = getPaddingBottom() + getPaddingTop();

        //获取一行的宽度
        int lineWidth = getPaddingLeft();

        List<View> childViews = new ArrayList<>();
        mChildViews.add(childViews);

        //子view的高度不一致
        int maxHeight = 0;

        for (int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);
            //自行这段话就会执行child的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            if (lineWidth + (childView.getMeasuredWidth() + params.rightMargin + params.leftMargin) > width) {
                //换行累加高度
                height += childView.getMeasuredHeight() + params.topMargin + params.bottomMargin + childView.getPaddingTop() + childView.getPaddingBottom();
                lineWidth = childView.getMeasuredWidth() + params.rightMargin + params.leftMargin + childView.getPaddingRight() + childView.getPaddingLeft();
                childViews = new ArrayList<>();

                mChildViews.add(childViews);

            } else {
                lineWidth += childView.getMeasuredWidth() + params.rightMargin + params.leftMargin + childView.getPaddingRight() + childView.getPaddingLeft();
                maxHeight = Math.max(childView.getMeasuredHeight() + params.topMargin + params.bottomMargin + childView.getPaddingTop() + childView.getPaddingBottom(), maxHeight);
            }
            childViews.add(childView);
            height += maxHeight;


        }
        setMeasuredDimension(width, height);
        Log.e(TAG, "onLayout:width " + width);
        Log.e(TAG, "onLayout:mChildViews " + mChildViews.size());

    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top = getPaddingTop(), right, bootom;

        for (List<View> childViews : mChildViews) {
            left = getPaddingLeft();
            for (View childView : childViews) {
                ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();


                left += params.leftMargin + childView.getPaddingLeft();
                int childTop = top + params.topMargin + childView.getPaddingTop();
                right = childView.getMeasuredWidth() + left;
                bootom = childView.getMeasuredHeight() + childTop;

                childView.layout(left, childTop, right, bootom);
                Log.e(TAG, "onLayout:left " + left + "top" + top + "right" + right + "bootom" + bootom);
                //left叠加
                left += childView.getMeasuredWidth() + params.rightMargin + childView.getPaddingRight();
            }
            // 不断的叠加top值
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childViews.get(0).getLayoutParams();
            top += childViews.get(0).getMeasuredHeight() + params.topMargin + params.bottomMargin;
        }
    }

    public void setAdapter(BaseAdapter adapter) {

        if (adapter == null) {
            return;
        }
        //清空所有的view
        removeAllViews();
        mAdapter = adapter;
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            //通过位置获取view
            View view = mAdapter.getView(i, this);
            addView(view);

        }


    }


}
