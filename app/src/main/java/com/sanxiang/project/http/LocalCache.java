package com.sanxiang.project.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.sanxiang.project.util.EncryptUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/3/14.
 */
public class LocalCache {

    public static String filePath =
            Environment.getExternalStorageDirectory().getAbsolutePath()+"/sx/cache/pics";


    public static Bitmap getBitmap(String url){

        String md5Url = EncryptUtils.getMD5(url);


        File file = new File(filePath,md5Url);

        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());

        MemoryCache.setBitmap(url,bitmap);  //缓存到内存，不然若内存没有会一直读本地的

        return bitmap;

    }

    public static void setBitmap(String url,Bitmap bitmap){

        String md5Url = EncryptUtils.getMD5(url);
        File file = new File(filePath,md5Url);
        File parent = file.getParentFile();

        if(!parent.exists()){
            parent.mkdirs();
        }

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
