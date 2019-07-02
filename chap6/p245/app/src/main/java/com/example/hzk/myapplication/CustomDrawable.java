package com.example.hzk.myapplication;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by hzk on 2019/6/19.
 */

public class CustomDrawable extends Drawable {
    private Paint paint;
//    参考了ShapeDrawable和BitmapDrawable的源码（源码的重要性）
    //主要方法，和View的draw方法相似。在画布上画圆。
    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect r = getBounds();
        float cx = r.exactCenterX();
        float cy = r.exactCenterY();
        canvas.drawCircle(cx, cy, Math.min(cx, cy), paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
