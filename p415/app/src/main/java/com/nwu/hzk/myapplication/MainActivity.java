package com.nwu.hzk.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.image_pc1);
//        第二种设置图片方式
//        iv1.setImageResource(R.mipmap.test);
//        第三种设置图片方式
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
//        iv1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.test, options));
//        第四种设置图片方式
//        将mipmap封装成drawable对象
//        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.test);
//        iv1.setImageDrawable(drawable);


        iv1.setImageBitmap(decodeSampleBitmapfromResorece(getResources(),R.mipmap.test,300,100));
        //获取当前进程的可用内存
//        int maxmemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        Log.i(TAG, "onCreate: maxmemory"+maxmemory);
//        //缓存容量的大小为当前可用内存的1/8
//        int cachesize = maxmemory / 8;
//        Log.i(TAG, "onCreate: cachesize"+cachesize);
//        LruCache mMapCache = new LruCache<String, Bitmap>(cachesize) {
////            sizeOf则完成了bitmap对象的大小计算,计算缓存对象的大小
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
//                //除以1024也是为了将其 单位转换成KB
//                return value.getRowBytes() * value.getHeight() / 1024;
//            }
//        };
//        Object t1 = new Object();
//        mMapCache.put(t1,BitmapFactory.decodeResource(getResources(), R.mipmap.test, options));
//        mMapCache.get(t1);
//
//        long DISK_CACHE_SIZE = 1024 * 1024 *50;
//        File diskCacheDir = getDiskCacheDir(this,"bitmap");
//        if(!diskCacheDir.exists()){
//            diskCacheDir.mkdirs();
//        }
//        try {
//            mDiskLruCache = DiskLruCache.open(diskCacheDir,1,1,DISK_CACHE_SIZE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private Bitmap decodeSampleBitmapfromResorece(Resources res, int resId, int reqwidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //将inJustDecodeBounds
        //
        //
        // 参数设置为true；
        options.inJustDecodeBounds = true;
        //加载图片
        BitmapFactory.decodeResource(res, resId, options);
        //从BitmapFactory.Options中取出图片的原始宽高；
        options.inSampleSize = CalculateInSampleSize(options, reqwidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int CalculateInSampleSize(BitmapFactory.Options options, int reqwidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqwidth) {
            final int halfHeight = height / 2;
            final int halfwidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfwidth / inSampleSize) >= reqwidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
