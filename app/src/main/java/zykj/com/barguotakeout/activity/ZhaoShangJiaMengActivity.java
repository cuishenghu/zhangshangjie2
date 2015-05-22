package zykj.com.barguotakeout.activity;

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
 * lss 2015/5/15 招商加盟
 */
public class ZhaoShangJiaMengActivity extends CommonActivity implements View.OnClickListener {
    ImageView im_zsjm_back;
    Button bt_zsjm_tijiao;
    EditText et_zsjm_xiangfa;
    TextView tv_zhaoshanginfo;
    TextView tv_tijiaozhaoshang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_shang_jia_meng);
        initView();
        getZhaoShangInfo();
    }

    private void initView() {
        im_zsjm_back = (ImageView)findViewById(R.id.im_zsjm_back);
        bt_zsjm_tijiao = (Button)findViewById(R.id.bt_zsjm_tijiao);
        et_zsjm_xiangfa = (EditText)findViewById(R.id.et_zsjm_xiangfa);
        tv_zhaoshanginfo = (TextView)findViewById(R.id.tv_zhaoshanginfo);
        tv_tijiaozhaoshang = (TextView)findViewById(R.id.tv_tijiaozhaoshang);
        im_zsjm_back.setOnClickListener(this);
        bt_zsjm_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_zsjm_back:
                this.finish();
                break;
            case R.id.bt_zsjm_tijiao:
                //提交按钮操作（todo）
                if (et_zsjm_xiangfa.getText().toString().equals(null)){
                    ToastUTil.shortT(ZhaoShangJiaMengActivity.this, "招商信息不能为空！");
                }else{
                    CommitZhaoShangInfo();
                }

                break;
        }
    }

    public void getZhaoShangInfo(){//获得招商信息
        HttpUtil.getZhaoShangInfo(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                JSONObject json = (JSONObject) JSON.parse(response);
                JSONArray data = json.getJSONArray("data");
                JSONObject joj = data.getJSONObject(0);
                tv_zhaoshanginfo.setText(joj.getString("title"));
                tv_tijiaozhaoshang.setText(joj.getString("content"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(ZhaoShangJiaMengActivity.this, "真不幸，诚聘信息获取失败!");
            }
        });
    }

    public void CommitZhaoShangInfo(){//提交招商信息
        RequestParams params=new RequestParams();
        params.add("userid", Mapplication.getModel().getUserid());
        params.add("content", et_zsjm_xiangfa.getText().toString());
        HttpUtil.getcommitZhaoShang(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String response = new String(responseBody);
                ToastUTil.shortT(ZhaoShangJiaMengActivity.this, "提交招商信息成功!");
                ZhaoShangJiaMengActivity.this.finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(ZhaoShangJiaMengActivity.this, "真不幸，提交信息失败!");
            }
        }, params);
    }
}
