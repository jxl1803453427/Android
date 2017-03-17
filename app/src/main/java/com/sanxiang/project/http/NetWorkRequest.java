package com.sanxiang.project.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/3/14.
 */
public class NetWorkRequest{

    private Context context;
    private ProgressDialog progressDialog;
    private HttpBack httpBack;
    private String url;

    public NetWorkRequest(Context context){
        this.context = context;

    }
    public NetWorkRequest(){
    }

    private final static int RES_TYPE_JSON = 1;

    private final static int RES_TYPE_BITMAP = 2;


    private void onLoading(){
        if(context != null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("下载图片");
            progressDialog.setMessage("正在下载，请稍后。。。");
            progressDialog.setMax(100);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();

        }
    }

    private void onLaodingOver(){
        if(progressDialog != null){

            progressDialog.dismiss();
        }
    }

    public NetWorkRequest url(String url){

        this.url = url;

        return this;

    }
    public void doGet(HttpBack httpBack){
        this.httpBack = httpBack;
        onLoading();
        request(RES_TYPE_JSON);

    }

    public void doPost(String url){
        onLoading();
        request(RES_TYPE_JSON);

    }

    public void getImageBitmap(String url,HttpBack httpBack){
        this.httpBack = httpBack;
        this.url = url;
        request(RES_TYPE_BITMAP);
    }

    private void request(final int resType){

        MYAsyncTask asyncTask = new MYAsyncTask() {
            @Override
            public void onAsyncResponse(byte[] bytes) {
                if(bytes == null){
                    return;
                }
                switch (resType){

                    case RES_TYPE_JSON:
                        String json = new String(bytes);
                        onResponseJson(json);
                        break;

                    case RES_TYPE_BITMAP:

                        Bitmap bitmap =  BitmapFactory.decodeByteArray(bytes,0,bytes.length);

                        httpBack.onResBitmap(bitmap);
                        break;
                }
            }

            @Override
            public void onUpdateProgress(int progress) {
                super.onUpdateProgress(progress);

                httpBack.onUpdateProgress(progress);
            }


        };

        asyncTask.execute(url);

    }

    public void onResponseJson(String json){
        onLaodingOver();
        Log.e("-----------","---==="+json);
        httpBack.onRes(json);

    }

}
