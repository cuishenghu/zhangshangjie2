package zykj.com.barguotakeout.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.http.HttpUtil;

/**
 * lss 2015/5/15 诚聘
 */
public class ChengPinActivity  extends CommonActivity implements View.OnClickListener {
    ImageView im_chengpin_back;
    Button bt_chengpin_tijiao;
    EditText et_chengpin_xiangfa;
    TextView tv_zhiwei;
    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheng_pin);
        initView();
        GetChengPinInfo();
    }

    private void initView() {
        im_chengpin_back = (ImageView)findViewById(R.id.im_chengpin_back);
        bt_chengpin_tijiao = (Button)findViewById(R.id.bt_chengpin_tijiao);
        et_chengpin_xiangfa = (EditText)findViewById(R.id.et_chengpin_xiangfa);
        tv_zhiwei = (TextView)findViewById(R.id.tv_zhiwei);
        tv_content = (TextView)findViewById(R.id.tv_content);
        im_chengpin_back.setOnClickListener(this);
        bt_chengpin_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_chengpin_back:
                this.finish();
                break;
            case R.id.bt_chengpin_tijiao:
                //提交按钮操作（）
                if (et_chengpin_xiangfa.getText().toString().equals(null)){
                    ToastUTil.shortT(ChengPinActivity.this, "诚聘信息不能为空");
                }else{
                    CommitChengPinInfo();
                }
                break;
        }
    }

    public void GetChengPinInfo(){//获取诚聘信息
        HttpUtil.getChengPinInfo(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                JSONObject json = (JSONObject) JSON.parse(response);
                JSONArray data = json.getJSONArray("data");
                JSONObject joj = data.getJSONObject(0);
                tv_zhiwei.setText(joj.getString("zhiwei"));
                tv_content.setText(joj.getString("content"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(ChengPinActivity.this, "真不幸，诚聘信息获取失败!");
            }
        });
    }

    public void CommitChengPinInfo(){//提交诚聘信息
        RequestParams params=new RequestParams();
        params.add("userid", Mapplication.getModel().getUserid());
        params.add("content", et_chengpin_xiangfa.getText().toString());
        HttpUtil.getcommitChengPin(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                ToastUTil.shortT(ChengPinActivity.this, "提交诚聘信息成功!");
                ChengPinActivity.this.finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(ChengPinActivity.this, "真不幸，提交信息失败!");
            }
        }, params);
    }
}
