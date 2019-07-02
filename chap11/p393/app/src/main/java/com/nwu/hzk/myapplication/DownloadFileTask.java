package com.nwu.hzk.myapplication;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by hzk on 2019/6/27.
 */
//该类主要用于模拟文件下载过程，其输入参数类型为URL，后台任务进程参数为Integer，后台任务的返回结果类型为Long。
public class DownloadFileTask extends AsyncTask<URL, Integer, Long> {
    //1.onPreExecute在后台任务开始执行之前进行调用，用于界面的初始化操作，譬如显示一个进度条对话框等。
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //2.该方法中的所有代码都会在子线程中运行，在这里处理所有耗时任务，在这里是不可以更新UI元素，如果需要反馈当前任务的执行进度，
    // 可以调用publishProgress(Progress...)方法来完成
    //参数不定。
    //用来执行具体任务并通过publishProgress来更新进度。同时还需要判断下载任务是否被外界取消了。下载完成后返回结果：下载的总字节数。
    //线程池中执行。
    @Override
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalsize = 0;
        for (int i = 0; i < count; i++) {
            totalsize += Downloader.dowdloadFile(urls[i]);
            publishProgress((int) (i / (float) count * 100));
            //在主线程中执行，当异步任务被取消时，onCancelled将会被调用，onPostExecute将不会被调用。
            if (isCancelled())
                break;
        }
        return totalsize;
    }
    //3.后台调用了publishProgress(Progress...)方法后，onProgressUpdate将会被很快调用，其方法中携带的参数是从后台任务中传递过来的，
    // 在这里对UI进行更新，利用参数中的数值可以对界面元素进行相应的更新。
    //更新界面中的下载进度，在主线程中。当publishProgress被调用时，此方法会被调用。
    @Override
    protected void onProgressUpdate(Integer... values) {
        setProgressPercent(values[0]);
    }

    private void setProgressPercent(Integer value) {
    }

    //4.当后台任务执行完毕后并使用return语句返回后，该方法很快被调用。返回数据会作为参数传递到此方法中。可利用返回数据来进行UI操作。
    //譬如：提醒任务执行结果，以及关闭进度条对话框等。
    //运行于主线程，在界面上做一些提示，譬如弹出一个对话框告知用户下载已完成。
    @Override
    protected void onPostExecute(Long aLong) {

    }
}
