package com.example.hzk.p202;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 实现了一个具有圆形效果的自定义View，它会在自己的中心点以宽高的最小值为直径绘制一个红色的施实心圆。
 * TODO: document your custom view class.
 */
public class CirecleView extends View {
    //
    private int mColor = Color.RED;
    private Paint mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CirecleView(Context context) {
        super(context);
        init();
    }

    public CirecleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CirecleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //加载自定义属性集合CircleView
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CirecleView);
        //解析CircleView属性集合中的circle_color属性，它的id刚才已经定义了
        mColor = a.getColor(R.styleable.CirecleView_circle_color,Color.RED);
        //解析完成后，通过recycle来释放资源
        a.recycle();
        init();
    }

    private void init() {
        // Load attributes
        mpaint.setColor(mColor);
    }

    //解决wrap_content不起作用的问题
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode== MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSpec,200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
//        int width = getWidth();
//        int height = getHeight();
//        int radius = Math.min(width, height) / 2;
//        canvas.drawCircle(width/2,height/2,radius,mpaint);
        //解决padding不起作用的问题
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mpaint);
        //Padding 为内边框，指该控件内部内容，如文本/图片距离该控件的边距
//        Margin 为外边框，指该控件距离边父控件的边距
    }
}
