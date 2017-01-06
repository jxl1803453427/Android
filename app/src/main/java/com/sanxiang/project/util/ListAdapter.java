package com.sanxiang.project.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ListAdapter<T> extends BaseAdapter {
    private List<T> data;
    private LayoutInflater inflater;
    private int resId;


    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {

        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public ListAdapter(List<T> data, Context context, int resId) throws Exception {

        super();
        if (data == null || context == null || resId == 0) {
            throw new Exception("适配器异常");
        }
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = ViewHolder.getViewHolder(inflater, resId, convertView, parent);
        setViewContent(vh, data.get(position));
        return vh.getConvertView();
    }

    public abstract void setViewContent(ViewHolder vh, T item);

}
