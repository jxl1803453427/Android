package com.sanxiang.project.http;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sanxiang.project.MainActivity;
import com.sanxiang.project.MyApplication;
import com.sanxiang.project.R;
import com.sanxiang.project.util.HttpUtils;
import com.sanxiang.project.util.son;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;


//  华为应用内部存储目录    // /data/user/0/com.sanxiang.project/files
//      外存目录      /storage/emulated/0/cache/pics
public class HttpActivity extends AppCompatActivity {
    private ImageView mImageView;

    private String url = "http://ww2.sinaimg.cn/mw690/69c7e018jw1e6hd0vm3pej20fa0a674c.jpg";

    private String jokerUrl = "http://api.laifudao.com/open/xiaohua.json";

    private List<Joker> jokers;

    private String url1 = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do?size=10&page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_http);

        mImageView = (ImageView) findViewById(R.id.http_image);


        son son = new son();

        Log.e("-----------",son.a+"---"+son.d+"--"+son.v+this.toString());

        HttpUtils.getFirstJson(this,url1, new HttpBack<List<Joker>>(new TypeReference<List<Joker>>(){}) {
            @Override
            public void onResponse(List<Joker> response) {
                for (int i= 0 ;i<response.size();i++){
                    Log.e("----",response.get(i).title);
                }
            }

            @Override
            public void onUpdateProgress(int i) {
                super.onUpdateProgress(i);
                Log.e("---------",i+"--");
            }
        });




    }


    public void downLoad(View view){
        ImageUtils.displayImage(mImageView,"http://imgs.haoservice.com/jokeimg/uploadfile/2016/0821/20160821092716154.jpg");
    }
    public void cancle(View view){
        Log.e("----","----------");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void display(View view){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }
}
