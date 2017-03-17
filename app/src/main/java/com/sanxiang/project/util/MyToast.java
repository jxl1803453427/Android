package com.sanxiang.project.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/23.
 */
public class MyToast extends Toast{
    public MyToast(Context context) {
        super(context);
    }

    public static void show(Context context,String str){
        makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
