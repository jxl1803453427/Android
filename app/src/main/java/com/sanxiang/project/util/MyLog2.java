package com.sanxiang.project.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/30.
 */
public class MyLog2 {
    private static boolean isDug = false;

    public static void log(String str){
        if(isDug){
            Log.e("log_e",str);
        }
    }
}
