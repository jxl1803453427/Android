package com.sanxiang.project.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.sanxiang.project.http.CallBack;
import com.sanxiang.project.http.HttpBack;
import com.sanxiang.project.http.MYAsyncTask;
import com.sanxiang.project.http.NetWorkRequest;

/**
 * Created by Administrator on 2017/3/14.
 */
public class HttpUtils {

    public static void getFirstJson(Context context,String url,HttpBack callBack){

        NetWorkRequest request = new NetWorkRequest(context);

        request.url(url).doGet(callBack);
    }


    public static void baseReq(String url, CallBack callBack){

    }
}
