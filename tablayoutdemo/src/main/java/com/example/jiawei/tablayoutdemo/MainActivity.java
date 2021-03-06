package com.example.jiawei.tablayoutdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * tablayout 和 viewpager 一个 adapter 就这样
 */
public class MainActivity extends AppCompatActivity {

    Context context=this;

    private static int ITEM_CALL = 1;
    TabLayout tablayout;
    ViewPager viewpager;
    TabPagerAdapter adapter;

    private int [] iconsArr={android.R.drawable.ic_dialog_alert,android.R.drawable.ic_dialog_dialer,
            R.drawable.select_call,R.drawable.select_call,
            android.R.drawable.ic_dialog_map,android.R.drawable.ic_input_get
    };
    private static final String[] DATA = {"AigeStudio", "Aige", "Studio", "Android", "Java", "Design"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = (TabLayout) findViewById(R.id.tl_tab);
        viewpager = (ViewPager) findViewById(R.id.vp_tab);

        //TabLayout.MODE_FIXED 是全部标签在一屏内不能滑动
        //TabLayout.MODE_SCROLLABLE 可根据大小 左右滑动
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        adapter = new TabPagerAdapter();
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (Integer key: mapView.keySet()) {
                    TextView textView = mapView.get(key);
                    if(key==tab.getPosition()){
                        textView.setTextColor(Color.RED);
                    }else{
                        textView.setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setIcons();//如果不需要图标 删掉此方法打开getpagetitle
    }

    private void setIcons() {
        for (int i=0;i<iconsArr.length;i++){//拿到每一个tab分别设置icon
            TabLayout.Tab tabCall = tablayout.getTabAt(i);
//            tabCall.setIcon(iconsArr[i]);//只设置一个图标
//            tabCall.setText(DATA[i]);//设置文字的颜色
            tabCall.setCustomView(getTabView(i));//自定义的tab
        }
    }

    private class TabPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return DATA.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tvContent = new TextView(MainActivity.this);
            tvContent.setText(DATA[position]);
            tvContent.setGravity(Gravity.CENTER);
            container.addView(tvContent, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
            return tvContent;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            ITEM_CALL=position;
//
//            return  DATA[ITEM_CALL];//如不需要文字 返回NULL即可

            return null;

        }
        
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        mapView.put(position,tv);
        tv.setText(DATA[position]);
        tv.setTextColor(Color.RED);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(iconsArr[position]);
        return view;
    }
    Map<Integer,TextView> mapView=new HashMap<Integer, TextView>();
}
