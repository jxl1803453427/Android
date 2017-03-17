package com.sanxiang.project.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sanxiang.project.R;
import com.sanxiang.project.util.MyLog;
import com.sanxiang.project.util.MyLog3;

public class CacheActivity extends AppCompatActivity {
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);

        int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;

        String url = "https://static.zyxr.com/g1/M00/02/D8/oYYBAFhsXWSAL6mLAAJLeF42Ymg306.jpg";

        mImageView = (ImageView) findViewById(R.id.cache_image);

        ImageSize imageSize = new ImageSize(200,200);

//        ImageLoader.getInstance().loadImage(url,imageSize, new ImageLoadingListener() {//或SimpleLoadingListenner
//            @Override
//            public void onLoadingStarted(String s, View view) {
//                MyLog3.log("12");
//            }
//
//            @Override
//            public void onLoadingFailed(String s, View view, FailReason failReason) {
//                MyLog3.log("13");
//            }
//
//            @Override
//            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                MyLog3.log("1");
//                mImageView.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onLoadingCancelled(String s, View view) {
//
//            }
//        });

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .showImageOnLoading(R.mipmap.ic_icon)
                .showImageOnFail(R.mipmap.error)
                .showImageForEmptyUri(R.mipmap.empty)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        ImageLoader.getInstance().displayImage(url,mImageView,options);

//        ImageLoader.getInstance().loadImage(url, imageSize, options, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String s, View view) {
//                MyLog3.log("111");
//                mImageView.setImageResource(R.mipmap.ic_icon);
//            }
//
//            @Override
//            public void onLoadingFailed(String s, View view, FailReason failReason) {
//                MyLog3.log("1222222");
//            }
//
//            @Override
//            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                MyLog3.log("13333333");
//                mImageView.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onLoadingCancelled(String s, View view) {
//                MyLog3.log("1");
//            }
//        });
    }
}
