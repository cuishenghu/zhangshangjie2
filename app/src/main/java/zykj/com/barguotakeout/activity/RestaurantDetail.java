package zykj.com.barguotakeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.HashSet;

import info.hoang8f.android.segmented.SegmentedGroup;
import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.AppLog;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.fragment.RestaurantMenuFragment;
import zykj.com.barguotakeout.fragment.ShopFragment;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.model.ResturantModel;

/**
 * Created by ss on 15-4-23.
 */
public class RestaurantDetail  extends CommonActivity implements  View.OnClickListener {

    public static final String KEY="restmodel";
    private ResturantModel model;
    private RestaurantMenuFragment menuFragment;
    private ShopFragment shopFragment;
    ImageView im_rest_shoucang;
    private int currentItem=1;
    String issc="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantdetail);
        model = getIntent().getParcelableExtra(KEY);
        if(model==null){
            return;
        }
        initView();
        initFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initFragment() {
        menuFragment = RestaurantMenuFragment.newInstance(model.getResid());
        shopFragment= ShopFragment.newInstance(model.getResid());
        getSupportFragmentManager().beginTransaction().add(R.id.frag_layout,menuFragment).
                add(R.id.frag_layout,shopFragment)
                .show(menuFragment).hide(shopFragment).commit();
    }

    private void initView() {
        SegmentedGroup group= (SegmentedGroup) findViewById(R.id.radios);
        im_rest_shoucang = (ImageView) findViewById(R.id.im_rest_shoucang);
        group.setTintColor(getResources().getColor(R.color.white),getResources().getColor(R.color.top_color));
        //group.setOnCheckedChangeListener(this);
        if (model.getIsCollected()==0){
            im_rest_shoucang.setImageResource(R.mipmap.shoucang);
        }else{
            im_rest_shoucang.setImageResource(R.mipmap.shoucanghou);
        }
        TextView textView= (TextView) findViewById(R.id.index_title);
        textView.setText(model.getName());

        RadioButton btn_1= (RadioButton) findViewById(R.id.radio_btn1);
        RadioButton btn_2= (RadioButton) findViewById(R.id.radio_btn2);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_1.setChecked(true);//默认打开时 选中第一个fragment
        im_rest_shoucang.setOnClickListener(this);
    }

    public void back(View v){
        finish();
    }



    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id){
            case R.id.radio_btn1:
                if(currentItem==1){
                    return;
                }
                getSupportFragmentManager().beginTransaction().hide(shopFragment).show(menuFragment).commit();
                currentItem=1;
                AppLog.i("menu1",currentItem+"");
                break;
            case R.id.radio_btn2:

                if(currentItem==2){
                    return;
                }
                getSupportFragmentManager().beginTransaction().hide(menuFragment).show(shopFragment).commit();
                currentItem=2;
                AppLog.i("menu2",currentItem+"");

                break;
            case R.id.im_rest_shoucang:

                if(TextUtils.isEmpty(Mapplication.getModel().getUsername())){
                    //还没有登录
                    ToastUTil.shortT(RestaurantDetail.this,"请先登录");
                }else {
                    //已经登陆
                    if (model.getIsCollected()==0){//未收藏
                        RequestParams params=new RequestParams();
                        params.add("resid", model.getResid());
                        params.add("userid", Mapplication.getModel().getUserid());
                        params.add("type", "1");
                        model.setIsCollected(1);
                        HttpUtil.getIsShoucang(new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                String response=new String(responseBody);
                                ToastUTil.shortT(RestaurantDetail.this,response);
//                                ToastUTil.shortT(RestaurantDetail.this,"添加收藏成功");
                                im_rest_shoucang.setImageResource(R.mipmap.shoucanghou);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                ToastUTil.shortT(RestaurantDetail.this, "网络连接失败");
                            }
                        }, params);

                    }else{//已收藏
                        ToastUTil.shortT(RestaurantDetail.this,"1111111111");
                        RequestParams params=new RequestParams();
                        params.add("resid", model.getResid());
                        params.add("userid", Mapplication.getModel().getUserid());
                        params.add("type", "0");
                        model.setIsCollected(0);
                        HttpUtil.getIsShoucang(new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                ToastUTil.shortT(RestaurantDetail.this,"shanchu收藏成功");
                                im_rest_shoucang.setImageResource(R.mipmap.shoucang);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                ToastUTil.shortT(RestaurantDetail.this, "网络连接失败");
                            }
                        }, params);
                    }
                }
                break;
        }

    }
}
