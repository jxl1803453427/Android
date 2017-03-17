package com.sanxiang.project;

/**
 * Created by Administrator on 2017/3/1.
 */
public class JniUtils {
    static {
        System.loadLibrary("test");//名称须根gradle配置相同
    }
    public native String getStringFromC();
    public native int add(int a,int b);
}
