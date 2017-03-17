package com.sanxiang.project.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/14.
 */
public class MemoryCache {

    public static LruCache<String,Bitmap> lruCache;

    static {
        long processMemory = Runtime.getRuntime().maxMemory();

        int lruCacheSize = (int) (processMemory / 8);

        lruCache = new LruCache<String,Bitmap>(lruCacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getRowBytes() *
                        value.getHeight();

                return byteCount;
            }
        };
    }

    public static Bitmap getBitmap(String url){


        return lruCache.get(url);
    }

    public static void setBitmap(String url,Bitmap bitmap){
        if(bitmap == null){
            return;
        }
        lruCache.put(url,bitmap);
    }


}
