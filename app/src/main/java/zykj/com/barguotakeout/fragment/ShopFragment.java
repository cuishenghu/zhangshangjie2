package zykj.com.barguotakeout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.apache.http.Header;

import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.AppLog;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.activity.ShopAssessActivity;
import zykj.com.barguotakeout.activity.ShopPhotoActivity;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.model.ResturantModel;

/**
 * Created by ss on 15-4-23.
 */
public class ShopFragment extends CommonFragment implements View.OnClickListener {

    private String resid="";
    private TextView tv_name;

    private static final String TAG=ShopFragment.class.getSimpleName();
    private ImageView iv_sjop;
    private RatingBar ratingBar;
    private TextView tv_songdashijian;
    private TextView tv_waisongfei;
    private TextView tv_qisongjia;
    private TextView tv_address;
    private TextView tv_photonum;
    private TextView tv_opentime;
    private RelativeLayout rl_comment;
    private RelativeLayout rl_photo;
    private TextView tv_commentnum;
    private ImageView iv_share;

    public static ShopFragment newInstance(String resid){
        ShopFragment shopFragment=new ShopFragment();
        Bundle bundle=new Bundle();
        bundle.putString("resid",resid);
        shopFragment.setArguments(bundle);
        return shopFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fagment_shao_detail,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resid=getArguments().getString("resid");
        initview();
        requestData();
    }

    private void initview() {
        tv_name = (TextView) getView().findViewById(R.id.tv_res_name);
        iv_sjop = (ImageView) getView().findViewById(R.id.iv_shop_header);
        ratingBar = (RatingBar) getView().findViewById(R.id.item_rating);
        tv_songdashijian = (TextView) getView().findViewById(R.id.tv_tv_03);
        tv_waisongfei = (TextView) getView().findViewById(R.id.tv_waisongfei);
        tv_qisongjia = (TextView) getView().findViewById(R.id.tv_qisongjia);
        tv_opentime = (TextView) getView().findViewById(R.id.tv_opentime);
        tv_address = (TextView) getView().findViewById(R.id.tv_01);
        tv_photonum = (TextView) getView().findViewById(R.id.tv_photonum);
        rl_comment = (RelativeLayout) getView().findViewById(R.id.rl_comment);
        rl_photo = (RelativeLayout) getView().findViewById(R.id.rl_photo);
        tv_commentnum = (TextView) getView().findViewById(R.id.tv_pingia);
        iv_share = (ImageView) getView().findViewById(R.id.iv_share);
        rl_comment.setOnClickListener(this);
        rl_photo.setOnClickListener(this);
        iv_share.setOnClickListener(this);

    }

    /**
     * 请求数据
     */
    private void requestData() {
        RequestParams params=new RequestParams();
        params.add("resid", resid);
        HttpUtil.getRestDetail(new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response=new String(responseBody);

                JSONObject json= (JSONObject) JSONObject.parse(response);
                JSONObject data=json.getJSONObject("data");
                ResturantModel model=JSONObject.parseObject(data.toJSONString(),ResturantModel.class);
                if(model==null){
                    AppLog.e(TAG,"model is null");
                    return;}
                AppLog.i(TAG,model.getName());
                tv_name.setText(model.getName());
                ImageLoader.getInstance().displayImage(model.getLogo(),iv_sjop);

                //评星
               ratingBar.setRating(Integer.valueOf( model.getTotalcredit()));
                tv_songdashijian.setText(String.format("%s分", model.getDeliverytime()));
                tv_waisongfei.setText(String.format("￥%s", model.getDeliveryprice()));
                tv_qisongjia.setText(String.format("￥%s",model.getBegindeliveryprice()));

                tv_address.setText(String.format("店铺地址:%s",model.getAddressname()));
                //tv_photonum.setText(String.format("(%s张)",model.get)); TODO 加入店铺相册

                tv_opentime.setText(String.format("营业时间:%s",model.getStart())+model.getEnd());
                tv_commentnum.setText(""); //TODO 加入评论
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(getActivity().isFinishing()){
                    return;
                }
                ToastUTil.shortT(getActivity(),"网络连接失败");
            }
        },params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_comment:
                //查看评论
                Intent aIntent = new Intent(getActivity(), ShopAssessActivity.class);
                aIntent.putExtra("resid",resid);
                startActivity(aIntent);
                break;
            case R.id.rl_photo:
                //查看相册
                Intent pIntent = new Intent(getActivity(), ShopPhotoActivity.class);
                pIntent.putExtra("resid",resid);
                startActivity(pIntent);
                break;
            case R.id.iv_share:
                //分享
                final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
// 设置分享内容
                mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
// 设置分享图片, 参数2为图片的url地址
                mController.setShareMedia(new UMImage(getActivity(),
                        "http://www.umeng.com/images/pic/banner_module_social.png"));
                //分享到QQ
                mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN, SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA,SHARE_MEDIA.TENCENT);
                UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104656844",
                        "t9wdH49qoNg84deS");
                qqSsoHandler.addToSocialSDK();
                //分享到微信
                String appID = "wx967daebe835fbeac";
                String appSecret = "5fa9e68ca3970e87a1f83e563c8dcbce";
                // 添加微信平台
                UMWXHandler wxHandler = new UMWXHandler(getActivity(),appID,appSecret);
                wxHandler.addToSocialSDK();

                mController.openShare(getActivity(), false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
