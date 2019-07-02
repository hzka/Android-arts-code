package com.example.hzk.p210;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hzk on 2019/6/17.
 */

public class HorizontalScrollViewEx extends ViewGroup {
    private int childrenSize;
    private int mChildWidth;
    public HorizontalScrollViewEx(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        //首先判断是否有子元素
        final int childrenCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        //如果没有子元素就直接把自己的宽高设置为零
        if (childrenCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            //如果高采用了wrap_content，那么高度就是第一个元素的高度。
            measureWidth = childView.getMeasuredWidth() * childrenCount;
            //如果宽采用了wrap_content，那么宽度就是所有子元素的宽度之和。
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measureWidth,measureHeight);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpecSize,measureHeight);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth() * childrenCount;
            setMeasuredDimension(measureWidth,heightSpecSize);
        }
//        不规范之处有两点：1.没有元素时不应该把宽高设置为零，应该根据LayoutParams的宽高来处理；
//        2.未考虑它的Padding和子元素的margin。
    }
//    首先遍历所有子元素，若果不是处于GONE状态下，通过Layout将其放在合适的位置上，位置是从左往右的，
//    但是仍然没有考虑padding和子元素的margin，这个也不是很规范，
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childleft = 0;
        final int childCount = getChildCount();
        childrenSize = childCount;
        for(int i= 0 ;i<childCount;i++){
            final View childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childleft,0,childleft+childWidth,childView.getMeasuredHeight());
                childleft+=childWidth;
            }
        }
    }

    //解决滑动冲突的外部拦截事件处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    //点击事件处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
