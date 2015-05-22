package zykj.com.barguotakeout.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;

import java.util.List;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.adapter.RestaurantAdapter;
import zykj.com.barguotakeout.http.EntityHandler;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.model.ResturantModel;

/**
 * lss 2015/5/15 收藏
 */
public class ShouCangActivity extends CommonActivity {

    private ListView listView;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTag(ShouCangActivity.class.getSimpleName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        initView();
        requestData();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.store_list);
    }

    private void requestData() {
        if(Mapplication.getModel().getUserid() == null){
            return;
        }
        final RequestParams params=new RequestParams();
        params.add("page","1");
        params.add("num","5");
        params.add("userid","23"/*Mapplication.getModel().getUserid()*/);
        HttpUtil.getcollections(new EntityHandler<ResturantModel>(ResturantModel.class) {
            @Override
            public void onReadSuccess(List<ResturantModel> list) {
                adapter = new RestaurantAdapter(ShouCangActivity.this, list);//收藏的Adapter已新建
                listView.setAdapter(adapter);
            }
        }, params);
    }

    public void back(View v){
        finish();
    }


}
