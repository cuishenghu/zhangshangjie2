package zykj.com.barguotakeout.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.http.SimpleHttpHandler;

/**
 * lss 2015/5/15 店铺相册
 */
public class ShopPhotoActivity extends CommonActivity {

    private String resid;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_photos);
        resid = getIntent().getStringExtra("resid");
        listView = (ListView)findViewById(R.id.photo_list);
        requestData();
    }

    public void requestData(){
        final List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        RequestParams params = new RequestParams();
        params.add("resid", resid);
        HttpUtil.getShopPhoto(new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String responseString=new String(responseBody, HTTP.UTF_8);
                    final JSONArray jsonArray = (JSONArray) JSON.parse(responseString);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("picurl",jsonObject.getString("picurl"));
                        map.put("introduction",jsonObject.getString("introduction"));
                        list.add(map);
                    }
                    //context,List, int resource, java.lang.String[] from, int[] to
                    SimpleAdapter adapter = new SimpleAdapter(ShopPhotoActivity.this,list,R.layout.item_photo,
                            new String[]{"picurl","introduction"},new int[]{R.id.photo_picurl,R.id.photo_introduction});
                    listView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        }, params);
    }
}
