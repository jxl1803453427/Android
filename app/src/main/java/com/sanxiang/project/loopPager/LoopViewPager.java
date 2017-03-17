package com.sanxiang.project.loopPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/2/17.
 */
public class LoopViewPager extends ViewPager{
    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int showTime = 3 * 1000;
    private Direction direction = Direction.LEFT;

    public void start(){
        stop();
        postDelayed(player,showTime);
    }

    public void stop(){
        removeCallbacks(player);
    }

    public void previous(){
        if (direction == Direction.RIGHT) {
            play(Direction.LEFT);
        } else if (direction == Direction.LEFT) {
            play(Direction.RIGHT);
        }
    }

    public void next(){
        play(direction);
    }

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    private synchronized void play(Direction direction) {
        // 拿到ViewPager的适配器
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {// 判空
            // Item数量
            int count = pagerAdapter.getCount();
            // ViewPager现在显示的第几个？
            int currentItem = getCurrentItem();
            switch (direction) {
                case LEFT:// 如是向左滚动的，currentItem+1播放下一个
                    currentItem++;

                    // 如果+到最后一个了，就到第一个
                    if (currentItem >= count)
                        currentItem = 0;
                    break;
                case RIGHT:// 如是向右滚动的，currentItem-1播放上一个
                    currentItem--;

                    // 如果-到低一个了，就到最后一个
                    if (currentItem < 0)
                        currentItem = count - 1;
                    break;
            }
            setCurrentItem(currentItem);// 播放根据方向计算出来的position的item
        }

        // 这就是当可以循环播放的重点，每次播放完成后，再次开启一个定时任务
        start();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
            }
        });
    }
}
