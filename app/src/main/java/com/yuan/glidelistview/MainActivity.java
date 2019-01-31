package com.yuan.glidelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by awei on 2017/12/13.
 */

public class MainActivity extends Activity implements TransmitHttpData {
    ListView listView;
    private Bitmap bitmap;
    //网络请求类
    private GetHttpData httpData;
    //list
    private List<Bean> mList;
    //适配器
    private MyAdapter adapter;
    private Bean bean;
    private static final int UPDATE_IMAGE = 1;
    private String url;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpData = (GetHttpData) new GetHttpData("http://v.juhe.cn/joke/randJoke.php?key=60fbc4de5e9c94872a10268487583390&type=pic", this).execute();
        initData();
    }

    private void initData() {
        listView=(ListView)findViewById(R.id.listview);
        //初始数组
        mList = new ArrayList<>();
    }

    @Override
    public void GetData(String data) {
        adapter = new MyAdapter(this, mList);
        parseJson(data);
        //将adapter的初始化放在这里是为了确保mList有数据

        listView.setAdapter(adapter);
    }

    private void parseJson(String data) {
        try {
            JSONObject object = new JSONObject(data);
            JSONArray result = object.optJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                bean = new Bean();
                JSONObject object1 = result.getJSONObject(i);
                //获得图片的url
                bean.setUrl(object1.optString("url"));
                mList.add(bean);
                //刷新数据
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}