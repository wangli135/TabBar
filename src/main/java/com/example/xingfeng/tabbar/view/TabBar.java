package com.example.xingfeng.tabbar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xingfeng.tabbar.R;

/**
 * 顶部条，包含多个选项
 * Created by Xingfeng on 2015/11/18.
 */
public class TabBar extends LinearLayout implements View.OnClickListener{

    /**
     * 选项未被选中时的文本颜色
     */
    private int normalColor;

    /**
     * 选项被选中时的文本的颜色
     */
    private int selectedColor;

    /**
     * 选项的显示文本数组
     */
    private CharSequence[] contents;

    /**
     * 选项
     */
    private TextView[] tabs;

    public interface  OnTabSelectedListener{

        /**
         * 选项被选中时的回调
         * @param v 视图
         */
        public void onTabSelected(View v);

    }

    private OnTabSelectedListener onTabSelectedListener;

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    public TabBar(Context context) {
        this(context, null);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TabBar);

        int n=ta.length();

        for(int i=0;i<n;i++){

            switch (ta.getIndex(i)){

                case R.styleable.TabBar_normalColor:

                    normalColor=ta.getColor(R.styleable.TabBar_normalColor, Color.WHITE);

                    break;
                case R.styleable.TabBar_selectedColor:

                    selectedColor=ta.getColor(R.styleable.TabBar_selectedColor,Color.RED);

                    break;
                case R.styleable.TabBar_contentEntries:

                    contents=ta.getTextArray(R.styleable.TabBar_contentEntries);

                    break;

            }

        }

        ta.recycle();

        initTabs();

        setSelected(0);//默认选中第1个选项
    }

    /**
     * 初始化选项
     */
    private void initTabs(){

        int num=contents.length;
        setWeightSum(num);

        tabs=new TextView[num];

        TextView tv=null;
        //创建Tab
        for(int i=0;i<num;i++){

            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT);
            lp.gravity= Gravity.CENTER_VERTICAL;
            lp.weight=1;
            tv=new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setText(contents[i]);
            tv.setTag(i);//以索引关联TextView
            tv.setOnClickListener(this);
            addView(tv,lp);
            tabs[i]=tv;
            tv=null;

        }


    }

    /**
     * 清空所有选项文本的颜色
     */
    private void cleraAllTextColor(){

        for(TextView tv:tabs){

            tv.setTextColor(normalColor);

        }

    }

    /**
     * 选中某个选项
     * @param index 选项下标
     */
    public void setSelected(int index){

        //清空
        cleraAllTextColor();
        
        //设置选项
        tabs[index].setTextColor(selectedColor);

    }

    @Override
    public void onClick(View v) {

        int tag=(Integer)v.getTag();

        setSelected(tag);

       if(onTabSelectedListener!=null){
           onTabSelectedListener.onTabSelected(v);
       }

    }
}
