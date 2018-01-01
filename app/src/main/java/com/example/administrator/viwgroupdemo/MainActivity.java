package com.example.administrator.viwgroupdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2018/1/1.
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mList = new ArrayList<>();
        mList.add("英雄联盟德玛西亚");
        mList.add("考虑考虑卡拉丁啊大大大圣诞快乐");
        mList.add("鞍山市大多");
        mList.add("灌灌灌灌");
        mList.add("法师法傻傻发顺丰");
        mList.add("萨达");
        mList.add("按时奥术大师大所多ad");
        mList.add("噶发傻是否");
        mList.add("按时送达十多个");
        mList.add("英雄联盟德玛西亚");
        mList.add("考虑考虑卡拉丁啊大大大圣诞快乐");
        mList.add("鞍山市大多");
        mList.add("灌灌灌灌");
        mList.add("法师法傻傻发顺丰");
        mList.add("萨达");
        mList.add("按时奥术大师大所多ad");
        mList.add("噶发傻是否");
        mList.add("按时送达十多个");

        mTabLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }
            @Override
            public View getView(int position, ViewGroup parent) {
                TextView textView = (TextView) LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.textiew, parent, false);
                textView.setText(mList.get(position));
                // 和我们listview差不多
                return textView;
            }
        });
    }
}
