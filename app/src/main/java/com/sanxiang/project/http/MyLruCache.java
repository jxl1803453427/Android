package com.sanxiang.project.http;

import android.graphics.Bitmap;
import android.util.LruCache;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author dpb on 16/1/14.
 *
 * @created 16/1/14.
 *
 * LruCache以键值对的形式，初始化时，需要设置缓存的大小K，
 * 超过这个大小的数据将会被清除。注意：清除的数据，是那些被先加入的数据。
 * LruCache内部的数据结构是LinkedHashMap存储的。
 * 这样，LruCache就达到了缓存最近put的K个数据。
 *
 * 实现原理：
 *    1.首先会给该LruCache对象设置一个阀值。
 *    2.通过put方法向该对象中添加缓存数据，在添加的时候
 *      会根据保存文件的大小和以使用的空间来判断是否超过了
 *      阀值，如果超过会将最近最少使用的移除缓存。我们会将移除
 *      的信息保存在软引用中。
 *    3.查询数据是否在LruCache中有缓存的时候我们会先在LruCache
 *      中查找，也就是强引用中查找，如果找到直接使用，否则再在软引用中
 *      查找，如果找到直接使用，并将该信息添加到强引用中去。
 *
 *
 * size  在添加和删除的过程中都会被更新
 *       在safeSizeOf中更新该值
 */
public class MyLruCache extends LruCache<String,Bitmap>{

    // 缓存中的软引用集合

    private HashMap<String,SoftReference<Bitmap>> hashMap;
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MyLruCache(int maxSize) {
        // maxSize 表示这个缓存内存放的最大值
        super(maxSize);
        hashMap = new HashMap<>();
    }

    /**
     * 返回用户定义的item的大小，默认返回1代表item的数量，最大size就是最大item值
     * @param key
     * @param value
     * @return
     */
    @Override
    protected int sizeOf(String key, Bitmap value) {
        // Bitmap的一个像素是4个字节
//        LogUtil.e("tag","总的字节数"+value.getByteCount());
        return value.getByteCount();
    }

    /**
     *
     * @param evicted
     *              true---为释放空间被删除；
     *              false---put或remove导致
     *              当在强引用中我们需要插入一个新的或者删除某个对象，
     *              我们会把该对象先保存在软引用中。
     * @param key
     * @param oldValue
     * @param newValue
     */
    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        // TODO 将oldValue添加到软引用中
        if(evicted){
            SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(oldValue);
            hashMap.put(key,softReference);
        }
    }

    /**
     * 从软引用中获取数据
     * @return
     */
    public HashMap<String,SoftReference<Bitmap>> getBitemapToSoftReference(){
        return hashMap;
    }


    /**
     * 从内存缓存中获取图片
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemoryCache(String key){
        // TODO 1.先从强引用中获取
        Bitmap bitmap = this.get(key);
        if(bitmap != null){
//            LogUtil.e("tag","== 从强引用中获取到了");
            return bitmap;
        }else{
            // TODO 2.如果强引用中不存在，那么在软引用中检查，
            //        如果有的话放入强引用中，删除弱引用中的
            SoftReference<Bitmap> softReference = hashMap.get(key);
            if(softReference != null)
            {
                // 从软引用中获取出来图片
                bitmap = softReference.get();
//                LogUtil.e("tag","从软引用中获取到图片...");
                if (bitmap != null)
                {
                    // TODO 如果不为空，添加到强引用中去
                    this.put(key, bitmap);
//                    LogUtil.e("tag","===添加到强引用中");
                    // TODO 从软引用中移除
                    hashMap.remove(key);
                    return bitmap;
                }
            }
        }
        return null;
    }
}
