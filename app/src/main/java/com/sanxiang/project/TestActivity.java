package com.sanxiang.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sanxiang.project.util.MyLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity {
    private boolean a = true;
    private ScrollView mScrollView;

    private static MyLog myLog = new MyLog();

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mTextView = (TextView) findViewById(R.id.test);

        String str = "~`*&…………%￥#@！~）（——+    +_)(*&^%$#@!~我的老家:还得你好啊*啊【】[]";
        mTextView.setText(stringFilter(str));



    }

    public void click(View view)
    {
        mTextView.setText("测试");
    }

    @Override
    protected void onResume() {
        super.onResume();

        final TextView test = (TextView) findViewById(R.id.test);
        test.measure(0,0);
        int i = test.getMeasuredHeight();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("----------","destory");
    }

    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
