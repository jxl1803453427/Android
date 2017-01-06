package com.sanxiang.project.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyLog3 {
    private static boolean isDug = true;

    public static void log(String str){
        if(isDug){
            Log.e("TAG",str);
        }
    }
}
