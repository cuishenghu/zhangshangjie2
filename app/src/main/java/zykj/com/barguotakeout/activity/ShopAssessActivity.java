package zykj.com.barguotakeout.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.adapter.ShopAssessAdapter;
import zykj.com.barguotakeout.http.EntityHandler;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.http.SimpleHttpHandler;
import zykj.com.barguotakeout.http.UrlContants;
import zykj.com.barguotakeout.model.ShopAssess;

/**
 * lss 2015/5/15 店铺相册
 */
public class ShopAssessActivity extends CommonActivity {

    private String resid;
    private ListView listView;
    private int page = 1;
    private int num = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_assess);
        resid = getIntent().getStringExtra("resid");
        listView = (ListView) findViewById(R.id.assess_list);
        requestData();
    }

    public void requestData() {
        final List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        RequestParams params = new RequestParams();
        params.add("resid", resid);//店铺id
        params.add("page", String.valueOf(page));//当前页
        params.add("num", String.valueOf(num));//每页数量
        params.add("iscontaintext", "0");//0为全部，1为非空
        HttpUtil.getcomments(new SimpleHttpHandler() {
            @Override
            public void onJsonSuccess(JSONObject json) {
                JSONObject jsonObject = json.getJSONObject(UrlContants.jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray(UrlContants.jsonData);
                List<ShopAssess> list = JSON.parseArray(jsonArray.toString(), ShopAssess.class);
                listView.setAdapter(new ShopAssessAdapter(ShopAssessActivity.this,list));
            }
        }, params);
    }
}