package zykj.com.barguotakeout.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.http.HttpErrorHandler;
import zykj.com.barguotakeout.http.HttpUtil;

/**
 * Created by ss on 15-4-17.
 */
public class RecommendActivity extends CommonActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTag(RecommendActivity.class.getSimpleName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        initView();
    }

    private void initView() {

    }


    public void back(View v){ finish(); }

}
