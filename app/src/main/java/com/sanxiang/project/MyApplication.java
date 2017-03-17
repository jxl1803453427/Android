package com.sanxiang.project;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sanxiang.project.util.MyLog3;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/2/8.
 */
public class MyApplication extends Application{

    private static Context context;

    private static float density;
    private static int densityDpi;

    @Override
    public void onCreate() {
        super.onCreate();

        initApp();

        initLibs();


    }

    private void initLibs() {
        //ImageLoader初始化
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);


        leakCanary();//捡漏
    }

    private void initApp(){
        context = this.getApplicationContext();

        DisplayMetrics metrics = getResources().getDisplayMetrics();//获取dpi
        density = metrics.density;
        densityDpi = metrics.densityDpi;
        MyLog3.log("本机密度："+density +"--------------"+densityDpi);



    }

    private void leakCanary(){

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }




    public static Context getAppContext(){
        return context;
    }



}
