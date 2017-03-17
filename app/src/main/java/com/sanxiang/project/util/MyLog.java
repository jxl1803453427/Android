package com.sanxiang.project.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyLog {
    private static boolean isDug = false;

    public static void log(String str){
        if(isDug){
            Log.e("TAG",str);

        }
    }
}
