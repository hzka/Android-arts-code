package com.example.hzk.fc341;

import android.os.AsyncTask;

/**
 * Created by hzk on 2019/6/23.
 */
//第一个参数是Void，执行AsyncTask无需传入参数给后台任务；第二个泛型参数是Integer，使用整形数据作为进度显示单位；
    //第三个参数是使用布尔类型用来反馈执行结果。
public class DownLoadTask extends AsyncTask<Void,Integer,Boolean>{
    //1.onPreExecute在后台任务开始执行之前进行调用，用于界面的初始化操作，譬如显示一个进度条对话框等。
    @Override
    protected void onPreExecute() {
        super.onPreExecute();//显示进度条
    }
    //2.该方法中的所有代码都会在子线程中运行，在这里处理所有耗时任务，在这里是不可以更新UI元素，如果需要反馈当前任务的执行进度，
    // 可以调用publishProgress(Progress...)方法来完成。
    @Override
    protected Boolean doInBackground(Void... params) {
        return null;//执行后台耗时任务
    }
    //3.后台调用了publishProgress(Progress...)方法后，onProgressUpdate将会被很快调用，其方法中携带的参数是从后台任务中传递过来的，
    // 在这里对UI进行更新，利用参数中的数值可以对界面元素进行相应的更新。
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);//在这里显示下载的进度
    }
    //4.当后台任务执行完毕后并使用return语句返回后，该方法很快被调用。返回数据会作为参数传递到此方法中。可利用返回数据来进行UI操作。
    //譬如：提醒任务执行结果，以及关闭进度条对话框等。
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);//关闭进度对话框并提示下载结果
    }
}
