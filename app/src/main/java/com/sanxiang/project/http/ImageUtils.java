package com.sanxiang.project.http;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/3/14.
 */
public class ImageUtils {

    public static void displayImage(final ImageView imageView, final String url){

        Bitmap bitmap = null;

        bitmap = MemoryCache.getBitmap(url);
        if(bitmap != null){
            Log.e("------------","从内存加载bitmap");
            imageView.setImageBitmap(bitmap);
            return;
        }

        bitmap = LocalCache.getBitmap(url);
        if(bitmap != null){
            Log.e("------------","------------------从本地加载bitmap");
            imageView.setImageBitmap(bitmap);
            return;
        }


        NetWorkRequest request = new NetWorkRequest();

        request.getImageBitmap(url, new HttpBack<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

            }

            @Override
            public void onResBitmap(Bitmap bitmap) {

                super.onResBitmap(bitmap);

                Log.e("------------","----------------------------------从网络加载");

                imageView.setImageBitmap(bitmap);

                MemoryCache.setBitmap(url,bitmap);

                LocalCache.setBitmap(url,bitmap);
            }
        });
    }

}
