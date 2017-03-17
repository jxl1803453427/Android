package com.sanxiang.project.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sanxiang.project.R;
import com.sanxiang.project.util.ListAdapter;
import com.sanxiang.project.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    private ListAdapter<String> adapter;
    private List<String> list;

    private String url = "http://ww2.sinaimg.cn/mw690/69c7e018jw1e6hd0vm3pej20fa0a674c.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        mListView = (ListView) findViewById(R.id.my_list);

        list = new ArrayList<>();
        list.add("");list.add("");list.add("");list.add("");list.add("");

        try {
            adapter = new ListAdapter<String>(list,this,R.layout.my_list_item) {
                @Override
                public void setViewContent(ViewHolder vh, String item) {
                    ImageView imageView = (ImageView) vh.getView(R.id.list_item_image);
                    ProgressBar progressBar = (ProgressBar) vh.getView(R.id.list_image_progress);

                    ImageUtils.displayImage(imageView,url);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        mListView.setAdapter(adapter);
    }
}
