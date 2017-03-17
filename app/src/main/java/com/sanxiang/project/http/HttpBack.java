package com.sanxiang.project.http;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


/**
 * Created by Administrator on 2017/3/15.
 */
public abstract class HttpBack<T> implements CallBack<T>{

    public  Class<T> tClass;

    public TypeReference<T> tTypeReference;

    private T t;

    public HttpBack(Class<T> tClass){

        this.tClass = tClass;

    }
    public HttpBack(){

    }
    public HttpBack(TypeReference<T> tTypeReference){

        this.tTypeReference = tTypeReference;

    }

    @Override
    public void onRes(String response) {

        if(tTypeReference != null){

            t = JSON.parseObject(response,tTypeReference);

        }else {

            t = JSON.parseObject(response,tClass);

        }

        onResponse(t);

    }

    public void onUpdateProgress(int i){

    }

    public abstract void onResponse(T response);

    public void onResBitmap(Bitmap bitmap){

    }
}
