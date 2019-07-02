package com.example.hzk.myapplication;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn_clicck01, btn_click02, btn_chick03, btn_click04;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_clicck01 = (Button) findViewById(R.id.btn_click01);
        btn_click02 = (Button) findViewById(R.id.btn_click02);
        btn_chick03 = (Button) findViewById(R.id.btn_click03);
        imageView = (ImageView) findViewById(R.id.image);
        btn_click04 = (Button) findViewById(R.id.btn_click04);
//        改变一个对象的translationY属性，让其沿着Y轴向上平移一个时间，默认时间完成。
        btn_clicck01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(imageView, "translationY", -imageView.getHeight()).start();
            }
        });
//        改变一个对象的背景颜色值，譬如改变view的背景，下面的动画是让view的背景从0xffff8080到0xff8080ff,动画会无限循环、反转。
        btn_click02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator colorAnim = ObjectAnimator.ofInt(imageView, "backgroundColor", 0xFFF8080, 0xFF8080FF);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatCount(ValueAnimator.REVERSE);
                colorAnim.start();
            }
        });

        btn_chick03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(imageView, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(imageView, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(imageView, "rotation", 0, -90),
                        ObjectAnimator.ofFloat(imageView, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(imageView, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(imageView, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(imageView, "scaleY", 1, 0.5f),
                        ObjectAnimator.ofFloat(imageView, "alpha", 1, 0.25f, 1)
                );
                set.setDuration(5 * 1000).start();
            }
        });

        btn_click04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.animatorxm);
//                set.setTarget(imageView);
//                set.start();
//                performAnimate();
//                performAnimate_01();
                performAnimator(btn_click02,btn_click02.getWidth(),500);
            }
        });
    }
    //用一个类来包装原始对象，间接提供get/set方法（使用ViewWrapper专门包装View，
    // 对View属性的width属性做动画，并修改内部宽度）
    private void performAnimate_01() {
        ViewWrapper wrapper = new ViewWrapper(btn_click02);
        ObjectAnimator.ofInt(wrapper,"width",500).setDuration(500).start();
    }
    //属性动画让它的宽度从当前的变成500px
    private void performAnimate() {
       ObjectAnimator.ofInt(btn_click02,"width",500).setDuration(500).start();
    }

    private void performAnimator(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获得当前动画的进度值，整形1-100之间
                int currentValue = (int) animation.getAnimatedValue();
                //获得当前进度占整个动画之间的比例，浮点0-1之间
                float fraction = animation.getAnimatedFraction();
                //直接使用整形估值器，通过比例计算宽度，然后再设置给按钮
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }
}
