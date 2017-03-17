package com.sanxiang.project.jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.sanxiang.project.JniUtils;
import com.sanxiang.project.R;

public class JniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        JniUtils utils = new JniUtils();
        int a = utils.add(1,2);

        if(!TextUtils.isEmpty(a+"")){
            Log.e("----------",a+"----");
        }
    }


}
