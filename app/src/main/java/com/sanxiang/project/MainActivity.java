package com.sanxiang.project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sanxiang.project.ScrollConflict.ConflictActivity;
import com.sanxiang.project.blueTooth.BlueToothActivity;
import com.sanxiang.project.cache.CacheActivity;
import com.sanxiang.project.customView.CustomActivity;
import com.sanxiang.project.customView.CustomView2Activity;
import com.sanxiang.project.customView.ViewHelperActivity;
import com.sanxiang.project.h5.H5Activity;
import com.sanxiang.project.handler.HandlerActivity;
import com.sanxiang.project.http.HttpActivity;
import com.sanxiang.project.jni.JniActivity;
import com.sanxiang.project.screen.ScreenActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initStatusBar();
    }
    private void initStatusBar(){

        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        // 或者 WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        winParams.flags |= bits;
        win.setAttributes(winParams);

        SystemBarTintManager manager = new SystemBarTintManager(this);
        Drawable drawable = getResources().getDrawable(R.color.red);
        if(drawable != null){
            manager.setStatusBarTintEnabled(true);
            manager.setTintDrawable(drawable);
        }
    }

    public void conflict(View view){
        Intent intent = new Intent(this, ConflictActivity.class);
        startActivity(intent);
    }
    public void CustomView(View view){
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }
    public void CustomView2(View view){
        Intent intent = new Intent(this, CustomView2Activity.class);
        startActivity(intent);
    }
    public void viewHelper(View view){
        Intent intent = new Intent(this, ViewHelperActivity.class);
        startActivity(intent);
    }

    public void handler(View view){
        Intent intent = new Intent(this, HandlerActivity.class);
        startActivity(intent);
    }
    public void cache(View view){
        Intent intent = new Intent(this, CacheActivity.class);
        startActivity(intent);
    }
    public void test(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    public void screen(View view){
        Intent intent = new Intent(this, ScreenActivity.class);
        startActivity(intent);
    }
    public void blueTooth(View view){
        Intent intent = new Intent(this, BlueToothActivity.class);
        startActivity(intent);
    }
    public void h5(View view){
        Intent intent = new Intent(this, H5Activity.class);
        startActivity(intent);
    }
    public void jni(View view){
        Intent intent = new Intent(this, JniActivity.class);
        startActivity(intent);
    }

    public void http(View view){
        Intent intent = new Intent(this, HttpActivity.class);
        startActivity(intent);
    }


    @Override
    public void finish() {
        super.finish();
        System.exit(0);
    }
}
