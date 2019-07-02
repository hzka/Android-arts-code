package com.example.hzk.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        //1.引用xml定义动画
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.testset);
//        btn_click.startAnimation(animation);
        //2.代码自定义动画
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//        alphaAnimation.setDuration(10000);
//        btn_click.setAnimation(alphaAnimation);
//        //3.帧动画
//        btn_click.setBackgroundResource(R.drawable.animationdrawable01);
//        AnimationDrawable drawable = (AnimationDrawable) btn_click.getBackground();
//        drawable.start();
//        4.ListView中设置动画
//        ListView listView = (ListView) layout.findViewById(R.id.list);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
//        LayoutAnimationController controller = new LayoutAnimationController(animation);
//        controller.setDelay(0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listView.setLayoutAnimation(controller);
        //Activity的切换效果
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.testset01,R.anim.testset01);
            }
        });
    }


}
