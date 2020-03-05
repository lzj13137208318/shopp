package com.example.shopping.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private int mHorizontalSpacing = dp2px(16); //每个item横向间距
    private int mVerticalSpacing = dp2px(8);    //每个item纵向间距
    private List<List<View>> allLines; //记录所有的行 一行一行的储存 用于layout
    private List<Integer> lineHeights = new ArrayList<>(); //记录每一行的行高， 用于layout

    public FlowLayout(Context context) {
        super(context);
        initMeasureParams();
    }
//这是通过反射找到控件必须实现
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMeasureParams();
    }
//非必须实现  defStyleAttr 自定义style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMeasureParams();
    }

    private void initMeasureParams(){
        allLines = new ArrayList<>();
        lineHeights = new ArrayList<>();
    }

    private void resetMeasureParams(){
        allLines.clear();
        lineHeights.clear();
    }

    //度量布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        resetMeasureParams();
        //先度量所有子View

        //获得所有的子View数量
        int childCount = getChildCount();
        //获取父类的内边距
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);  //ViewGrop解析的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec); //ViewGrop解析的高度

        ArrayList<View> lineViews = new ArrayList<>(); //保存一行中的所有的View
        int lineWidthUsed = 0; //记录这行已经使用了多宽的size
        int lineHeight = 0;   //一行的行高

        int parentNeedWidth = 0; //measure过程中，子view要求对父ViewGrop的宽
        int parentNeedHeight = 0; //measure过程中，子view要求对父ViewGrop的高

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //LayoutParams
            LayoutParams childLP = childView.getLayoutParams();

            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,
                    childLP.width);
            int childHightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom,
                    childLP.height);
            //子view的尺寸
            childView.measure(childWidthMeasureSpec, childHightMeasureSpec);

            //获取子View的宽高
            int childMeauredWidth = childView.getMeasuredWidth();
            int childmeasuredHeight = childView.getMeasuredHeight();

            //判断是否要换行
            if (childMeauredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth){

                allLines.add(lineViews);
                lineHeights.add(lineHeight);

                //一旦换行 我们就可以判断当前需要的宽和高 所以要记录下来
                parentNeedHeight = parentNeedHeight +lineHeight + mVerticalSpacing;// mVerticalSpacing
                parentNeedWidth = Math.max(parentNeedWidth , lineWidthUsed + mHorizontalSpacing);

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            //view 是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
            lineViews.add(childView);
            //每行都会有自己的宽和高
            lineWidthUsed = lineWidthUsed + childMeauredWidth + mHorizontalSpacing; //mHorizontalSpacing 间距
            lineHeight = Math.max(lineHeight , childmeasuredHeight);
        }

        //度量和保存自己
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;
        setMeasuredDimension(realWidth,realHeight);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取layout中的行数
        int lineCount = allLines.size();
        //
        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        //将所有的view全部布局完
        for (int i = 0; i < lineCount; i++) {
            List<View> linesViews = allLines.get(i);

            int height = lineHeights.get(i);
            for (int j = 0; j < linesViews.size(); j++) {
                View view = linesViews.get(j);
                int left =  curL;
                int top = curT;

                /*getWidth()和getMeasuredWidth()方法的区别
                *
                * getWidth()会在onlayout()方法之后调用
                * getMeasuredWidth() 会在 measulred()之后 也就是度量完之后调用
                * */


       /*         int right = left + view.getWidth();
                int bottom = top + view .getHeight(); */
                int right = left + view.getMeasuredWidth();
                int bottom = top + view .getMeasuredHeight();
                view.layout(left,top,right,bottom);
                curL = right + mHorizontalSpacing;
            }
            curT = curT + height + mVerticalSpacing;
            curL = getPaddingLeft();
        }
    }

    public static int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().getDisplayMetrics());
    }

}

