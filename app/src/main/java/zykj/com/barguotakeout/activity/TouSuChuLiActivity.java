package zykj.com.barguotakeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.fragment.MeFragment;
import zykj.com.barguotakeout.http.HttpUtil;

/**
 * lss 2015/5/15 投诉处理
 */
public class TouSuChuLiActivity extends CommonActivity implements View.OnClickListener {
    ImageView im_tscl_back;
    EditText et_tscl_text;
    Button bt_tscl_tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_su_chu_li);
        initView();
    }

    private void initView() {
        im_tscl_back = (ImageView) findViewById(R.id.im_tscl_back);
        et_tscl_text = (EditText) findViewById(R.id.et_tscl_text);
        bt_tscl_tijiao = (Button) findViewById(R.id.bt_tscl_tijiao);
        im_tscl_back.setOnClickListener(this);
        bt_tscl_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.im_tscl_back:
            this.finish();
            break;
        case R.id.bt_tscl_tijiao:
            //提交处理
            RequestParams params=new RequestParams();
            params.add("userid", Mapplication.getModel().getUserid());
            params.add("content", et_tscl_text.getText().toString());
            HttpUtil.getlodgeComplaint(new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    ToastUTil.shortT(TouSuChuLiActivity.this, "投诉成功,感谢您的支持。");
                    TouSuChuLiActivity.this.finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    ToastUTil.shortT(TouSuChuLiActivity.this, "网络连接失败");
                }
            }, params);
            break;

        }
    }
}
