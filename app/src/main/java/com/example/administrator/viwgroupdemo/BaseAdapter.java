package com.example.administrator.viwgroupdemo;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/1/1.
 */

public abstract  class BaseAdapter {

    //获取多少个条目
    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);
}
