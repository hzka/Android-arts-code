package com.example.hzk.myapplication;

import android.view.View;

/**
 * Created by hzk on 2019/6/20.
 */

class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View mTarget){
        this.mTarget = mTarget;
    }

    public int getWidth(){
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width){
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
