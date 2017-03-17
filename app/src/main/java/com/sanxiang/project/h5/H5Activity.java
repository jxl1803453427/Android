package com.sanxiang.project.h5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sanxiang.project.MainActivity;
import com.sanxiang.project.R;

public class H5Activity extends AppCompatActivity {
    private WebView mWebView;
    private String url = "http://www.baidu.com";
    boolean a = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        mWebView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);  //支持js
        //设定支持缩放
        //隐藏缩放工具

        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("-----------",url);

                if(a){
                    mWebView.loadUrl(url);
                    a = false;
                    return true;
                }else {
                    if(url.contains("http")){
                        Intent intent = new Intent(H5Activity.this,MainActivity.class);
                        startActivity(intent);
                        return true;
                    }else {
                        mWebView.loadUrl(url);
                        return false;
                    }
                }

            }
        });

    }
}
