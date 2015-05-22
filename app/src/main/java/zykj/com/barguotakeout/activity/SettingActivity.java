package zykj.com.barguotakeout.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.http.HttpUtil;

/**
 * Created by ss on 15-5-6.
 */
public class SettingActivity extends CommonActivity implements View.OnClickListener {

    private Button btn_logout;
    private RelativeLayout rl_zixundianhua;
    private JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();


    }

    private void initView() {
        btn_logout = (Button) findViewById(R.id.btn_set_logout);
        rl_zixundianhua = (RelativeLayout)findViewById(R.id.rl_zixundianhua);
        rl_zixundianhua.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    public void back(View v){
        finish();
    }

    /**
     * 退出登录
     */
    public void logout(){
        Mapplication.getModel().setUsername("");
        Mapplication.getModel().setPwd("");
        Mapplication.getModel().setUserid("");
        ToastUTil.shortT(this,"已退出");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.btn_set_logout:
                   //登出
                    logout();
                   break;
               case R.id.rl_zixundianhua:
                   //咨询电话
                   getDianHuaInfo();
                   break;
           }
    }

    public void getDianHuaInfo(){//获取咨询电话
        HttpUtil.getCompanyInfo(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                JSONObject json = (JSONObject) JSONObject.parse(response);
                data = json.getJSONObject("data");


        new AlertDialog.Builder(SettingActivity.this).setTitle("电话拨打提示框").setMessage("确认拨打电话？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+data.getString("contactphone")));
                        startActivity(intent);
                    }})
                .setNegativeButton("取消",null)
                .show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(SettingActivity.this, "真不幸，官网信息获取失败!");
            }
        });
    }
}
