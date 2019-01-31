package com.yuan.glidelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2019/1/22 0022.
 */

public class MyAdapter extends BaseAdapter {
    private LinearLayout mLinearLayout;
    private Context mContext;
    private List<Bean> mList;

    public MyAdapter(Context mContext, List<Bean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {//判断view是否可以重载
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mLinearLayout = (LinearLayout) inflater.inflate(R.layout.listview_item_image, null);
            //获取id
            viewHolder.imageView = mLinearLayout.findViewById(R.id.imageView);
            //使用glide加载图片
            Glide.with(mContext)
                    .load(mList.get(i).getUrl()) //加载地址
                    .placeholder(R.drawable.ic_launcher_background)//加载未完成时显示占位图
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.imageView);//显示的位置
            //标记当前view
            mLinearLayout.setTag(viewHolder);
        } else {//可以重载则直接使用原来的view
            mLinearLayout = (LinearLayout) view;
            viewHolder = (ViewHolder) mLinearLayout.getTag();
            //获取id
            viewHolder.imageView = mLinearLayout.findViewById(R.id.imageView);
            //使用glide加载图片
            Glide.with(mContext)
                    .load(mList.get(i).getUrl()) //加载地址
                    .placeholder(R.drawable.ic_launcher_background)//加载未完成时显示占位图
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.imageView);//显示的位置
        }
        return mLinearLayout;
    }

    //使用viewHolder缓存数据
    static class ViewHolder {
        ImageView imageView;
    }
}