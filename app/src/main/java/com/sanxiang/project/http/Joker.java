package com.sanxiang.project.http;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Joker {

    public String title;
    public String content;
    public String poster;
    public String url;

    @Override
    public String toString() {
        return "Joker{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", poster='" + poster + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
