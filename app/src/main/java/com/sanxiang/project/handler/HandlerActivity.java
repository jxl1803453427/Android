package com.sanxiang.project.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sanxiang.project.R;

public class HandlerActivity extends AppCompatActivity {
    private Handler mHandler;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = (TextView) findViewById(R.id.text);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e("--------------","222222222222222222");
            }
        };


        Log.e("----------",mHandler.getLooper()+ "--"+getMainLooper());


        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.e("-------","----------");
                        mTextView.setText("dksjglasgjlasdgkj");
                    }
                };
                SystemClock.sleep(1000);
                Looper.loop();
                handler.sendEmptyMessage(0);

                Log.e("----------",handler.getLooper()+ "--"+getMainLooper());
            }
        }).start();
    }
}
