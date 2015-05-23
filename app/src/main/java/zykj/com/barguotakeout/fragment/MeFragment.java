package zykj.com.barguotakeout.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMQQSsoHandler;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.activity.ChengPinActivity;
import zykj.com.barguotakeout.activity.CreditActivity;
import zykj.com.barguotakeout.activity.GongSiJianJieActivity;
import zykj.com.barguotakeout.activity.LoginActivity;
import zykj.com.barguotakeout.activity.MCountActivity;
import zykj.com.barguotakeout.activity.MyOrderActivity;
import zykj.com.barguotakeout.activity.SettingActivity;
import zykj.com.barguotakeout.activity.ShouCangActivity;
import zykj.com.barguotakeout.activity.TouSuChuLiActivity;
import zykj.com.barguotakeout.activity.ZhaoShangJiaMengActivity;
import zykj.com.barguotakeout.http.HttpErrorHandler;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.http.UrlContants;
import zykj.com.barguotakeout.model.User;
import zykj.com.barguotakeout.view.RoundImageView;

/**
 * Created by ss on 15-4-28.
 */
public class MeFragment extends CommonLoadFragment implements View.OnClickListener {

    private TextView tv;
    private LinearLayout ll_order;
    private RoundImageView rv_avator;
    private LinearLayout ll_top;
    private TextView tv_mobile;
    private RelativeLayout rl_me;
    private User user;
    private RelativeLayout rl_set;
    private RelativeLayout rl_me_zhaoshangjiameng;
    private RelativeLayout rl_me_chengpin;
    private LinearLayout ll_me_baguobi;
    private LinearLayout ll_me_shoucang;
    private RelativeLayout rl_me_guanwang;
    private RelativeLayout rl_me_gongsijianjie;
    private RelativeLayout rl_me_tousuchuli;

    public static final int LOGOUT=5;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_me,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpTopBar();
        initView();
        setTopView();
    }

    private void initView() {
        tv = (TextView) getView().findViewById(R.id.login);
        ll_order = (LinearLayout) getView().findViewById(R.id.ll_me_order);
        ll_top = (LinearLayout) getView().findViewById(R.id.ll_me_top);
        rv_avator = (RoundImageView) getView().findViewById(R.id.rv_me_avatar);//头像
        tv_mobile = (TextView) getView().findViewById(R.id.tv_me_mobile);
        rl_me = (RelativeLayout) getView().findViewById(R.id.rl_me_top);
        rl_set = (RelativeLayout) getView().findViewById(R.id.rl_me_setting);
        rl_me_zhaoshangjiameng = (RelativeLayout)getView().findViewById(R.id.rl_me_zhaoshangjiameng);
        rl_me_chengpin = (RelativeLayout)getView().findViewById(R.id.rl_me_chengpin);
        ll_me_baguobi = (LinearLayout)getView().findViewById(R.id.ll_me_baguobi);
        ll_me_shoucang = (LinearLayout)getView().findViewById(R.id.ll_me_shoucang);
        rl_me_guanwang = (RelativeLayout)getView().findViewById(R.id.rl_me_guanwang);
        rl_me_gongsijianjie = (RelativeLayout)getView().findViewById(R.id.rl_me_gongsijianjie);
        rl_me_tousuchuli = (RelativeLayout)getView().findViewById(R.id.rl_me_tousuchuli);
        rl_me.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        tv.setOnClickListener(this);
        rl_set.setOnClickListener(this);
        rl_me_zhaoshangjiameng.setOnClickListener(this);
        rl_me_chengpin.setOnClickListener(this);
        ll_me_baguobi.setOnClickListener(this);
        ll_me_shoucang.setOnClickListener(this);
        rl_me_guanwang.setOnClickListener(this);
        rl_me_gongsijianjie.setOnClickListener(this);
        rl_me_tousuchuli.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK){
            //刷新头像和
            setTopView();
        }
        if(requestCode==LOGOUT && resultCode==Activity.RESULT_OK){
            setTopView();
        }
    }



    @Override
    public void onClick(View v) {
        String userid = Mapplication.getModel().getUserid();
        switch (v.getId()){
            case R.id.ll_me_order:
                if(TextUtils.isEmpty(userid)){
                    //提醒用户登录
                    ToastUTil.showToastText(getActivity(), "提醒", "请先登录!", "确定");
                    return;
                }else{
                    //查看用户订单
                    startActivity(new Intent(getActivity(), MyOrderActivity.class));
                }
                break;
            case R.id.rl_me_top:
                //查看用户详情
                Intent intent = new Intent(getActivity(), MCountActivity.class);
                if(user != null && !TextUtils.isEmpty(userid)){
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
                break;
            case R.id.rl_me_setting:
                Intent intent1=new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent1,LOGOUT);
                break;
            case R.id.login:
                //登录界面
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), 0);
                break;
            case R.id.rl_me_zhaoshangjiameng:
                //招商加盟
                Intent intent2 = new Intent(getActivity(), ZhaoShangJiaMengActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_me_chengpin:
                //诚聘
                Intent intent3 = new Intent(getActivity(), ChengPinActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_me_baguobi:
                //巴国币
                if(TextUtils.isEmpty(Mapplication.getModel().getUserid())){
                    ToastUTil.showToastText(getActivity(),"提示","请先登录!","确定");
                    return;
                }
                RequestParams params = new RequestParams();
                //appkey:兑吧的appkey,必填；appsecret：兑吧的appsecret，必填；userid：用户的userid,必填；
                params.add("appkey",IndexFragment.APPKEY);
                params.add("appsecret",IndexFragment.APPSECRET);
                params.add("userid",Mapplication.getModel().getUserid());
                HttpUtil.getduibaurl(new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseString = new String(responseBody, HTTP.UTF_8);
                            JSONObject jsonObject = (JSONObject) JSON.parse(responseString);
                            JSONObject jsonData = jsonObject.getJSONObject("data");
                            String url = jsonData.getString("url");
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), CreditActivity.class);
                            intent.putExtra("navColor", "#0acbc1");    //配置导航条的背景颜色，请用#ffffff长格式。
                            intent.putExtra("titleColor", "#ffffff");    //配置导航条标题的颜色，请用#ffffff长格式。
                            intent.putExtra("url", url);    //配置自动登陆地址，每次需服务端动态生成。
                            startActivity(intent);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        ToastUTil.showToastText(getActivity(),"提醒","请求失败,请稍后重试!","确定");
                    }
                },params);
                break;
            case R.id.ll_me_shoucang:
                //收藏
                Intent intent5 = new Intent(getActivity(), ShouCangActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_me_guanwang:
                //官网
                getGuanWangInfo();
                break;
            case R.id.rl_me_gongsijianjie:
                //公司简介
                Intent intent7 = new Intent(getActivity(), GongSiJianJieActivity.class);
                startActivity(intent7);
                break;
            case R.id.rl_me_tousuchuli:
                //投诉处理
                Intent intent8 = new Intent(getActivity(), TouSuChuLiActivity.class);
                startActivity(intent8);
                break;
        }
    }

    public String getGuanWangInfo(){//获取官网信息

        HttpUtil.getCompanyInfo(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response=new String(responseBody);
                JSONObject json= (JSONObject) JSONObject.parse(response);
                JSONObject data=json.getJSONObject("data");
                Uri uri = Uri.parse(data.getString("websitelink"));
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                ToastUTil.shortT(getActivity(), "真不幸，官网信息获取失败!");
            }
        });

        return null;
    }

    public void setTopView(){
        if(TextUtils.isEmpty(Mapplication.getModel().getUsername())){
            //还没有登录
            tv.setVisibility(View.VISIBLE);
            hideTop();
        }else{
            //已经登陆
            showTop();
            RequestParams params=new RequestParams();
            params.add("phonenum",Mapplication.getModel().getUsername());
            params.add("password",Mapplication.getModel().getPwd());
            HttpUtil.login(new HttpErrorHandler() {
                @Override
                public void onRecevieSuccess(JSONObject json) {
                    //初始化头像 保存信息
                   JSONObject data=json.getJSONObject("data");
                   user=JSONObject.parseObject(data.toJSONString(),User.class);
                    Log.e("user-----------------",data.toString());
                    if(user.getAvatar()!=null){
                    ImageLoader.getInstance().displayImage(UrlContants.getUrl(user.getAvatar()),rv_avator);}
                }

                @Override
                public void onRecevieFailed(String status, JSONObject json) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            },params);
            tv.setVisibility(View.GONE);
            tv_mobile.setText(Mapplication.getModel().getUsername());
        }

    }

    private void hideTop(){
        rv_avator.setVisibility(View.GONE);
        ll_top.setVisibility(View.GONE);
    }
    private void showTop(){
        rv_avator.setVisibility(View.VISIBLE);
        ll_top.setVisibility(View.VISIBLE);
    }



}
