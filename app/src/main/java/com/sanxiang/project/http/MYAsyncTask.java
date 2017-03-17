package com.sanxiang.project.http;

import android.os.AsyncTask;
import android.os.SystemClock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/13.
 */

/**
 *
 *      坑啊---------------------------------------------------------------------
 * 异步任务结束前只能调用excute方法一次
 *
 * 多个异步任务是串行执行的，及一个任务执行完毕后才执行下一个任务
 *
 *
 */
public abstract class MYAsyncTask extends AsyncTask<String,Integer,byte[]>{


    public MYAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //初始化操作，例如进度对话框

    }

    @Override
    protected byte[] doInBackground(String... strings) {

        return getBytes(strings[0]);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        onUpdateProgress(values[0]);
    }

    @Override
    protected void onPostExecute(byte[] bytes) {

        onAsyncResponse(bytes);

    }

    public abstract void onAsyncResponse(byte[] bytes);

    public void onUpdateProgress(int progress){

    }


    private byte[] getBytes(String url){
        HttpURLConnection conn = null;
        InputStream in = null;
        ByteArrayOutputStream outputStream = null;
        try {
            URL mURL = new URL(url);
            // 打开HttpURLConnection连接
            conn = (HttpURLConnection) mURL.openConnection();
            // 设置参数
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            // 开启连接
            conn.connect();

            // 获得响应码
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                // 相应成功,获得网络返回来的输入流
                in = conn.getInputStream();
                int totalLength = conn.getContentLength();//数据总长度
                int currentLength = 0;
                int len = 0;
                byte[] buf = new byte[1024];

                outputStream = new ByteArrayOutputStream();

                while ((len=in.read(buf))!= -1){

                    outputStream.write(buf,0,len);
                    currentLength += len;

                    if(totalLength > 0){
                        int a = currentLength * 100 / totalLength;
                        publishProgress(a);
                    }
                    SystemClock.sleep(20);//------------------------------------------------------------debug
                }

                return outputStream.toByteArray();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 断开连接
            if(conn != null){
                conn.disconnect();
            }

            try {
                if(in != null){
                    in.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }




}
