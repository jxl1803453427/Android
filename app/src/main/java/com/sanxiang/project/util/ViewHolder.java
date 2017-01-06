package com.sanxiang.project.util;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {
	private SparseArray<View> map;
	private View convertView;
	private Object mTag;
	
	private ViewHolder(LayoutInflater inflater, int resId, ViewGroup parent){
		convertView = inflater.inflate(resId, parent, false);
		convertView.setTag(this);
		map = new SparseArray<View>();
	}
	
	public static ViewHolder getViewHolder(LayoutInflater inflater, int resId, View convertView, ViewGroup parent){
		if(convertView==null){
			return new ViewHolder(inflater, resId, parent);
		}
		return (ViewHolder) convertView.getTag();
	}
	
	public View getView(int viewId){
		View view = map.get(viewId);
		if(view == null){
			map.put(viewId, convertView.findViewById(viewId));
		}
		return map.get(viewId);
	}
	
	public void setText(int viewId,String text){
		TextView tv = (TextView) this.getView(viewId);
		tv.setText(text);		
	}
	
	public View getConvertView(){
		return convertView;
	}


	public Object getTag()
	{
		return mTag;
	}

	public void setTag(Object tag)
	{
		mTag = tag;
	}
	
}
