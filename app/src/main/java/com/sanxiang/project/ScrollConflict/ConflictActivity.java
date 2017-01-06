package com.sanxiang.project.ScrollConflict;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxiang.project.R;
import com.sanxiang.project.util.Constant;
import com.sanxiang.project.util.ListAdapter;
import com.sanxiang.project.util.MyLog;
import com.sanxiang.project.util.MyLog2;
import com.sanxiang.project.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ConflictActivity extends AppCompatActivity {
    private ListView mListView;
    private ListAdapter<String> adapter;
    private List<String> list;
    private TextView mTextView;

    /**
     * Scrollview中包含listview的状况，点击listview时是listview获得并处理了事件，
     * 当手往上或者下滑动了一小段距离时，这时Scrollview拦截了事件，并开始滑动
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict);
        mTextView = (TextView) findViewById(R.id.con_text);
        mListView = (ListView) findViewById(R.id.con_list);
        list = new ArrayList<>();
        list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
        list.add("7");list.add("8");list.add("9");list.add("10");

        try {
            adapter = new ListAdapter<String>(list,this,R.layout.con_list_item) {
                @Override
                public void setViewContent(ViewHolder vh, String item) {
                    vh.setText(R.id.list_item_text,item);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.log("item----------click");
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
//                if(i == AbsListView.OnScrollListener.SCROLL_STATE_FLING){
//                    MyLog2.log("fling");
//                }else if(i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
//                    MyLog2.log("idle");
//                }else if(i == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    MyLog2.log("scroll");
//                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    View firstVisibleItemView = mListView.getChildAt(0);
                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                        MyLog2.log("##### 滚动到顶部 #####");
                        Constant.listSlide = Constant.slide_top;

                    }
                } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = mListView.getChildAt(mListView.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == mListView.getHeight()) {
                        MyLog2.log("##### 滚动到底部 ######");
                        Constant.listSlide = Constant.slide_bottom;
                    }
                }

            }
        });

    }
}
