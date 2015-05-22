package zykj.com.barguotakeout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import zykj.com.barguotakeout.Mapplication;
import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.Utils.AppLog;
import zykj.com.barguotakeout.Utils.ImageUtil;
import zykj.com.barguotakeout.Utils.ToastUTil;
import zykj.com.barguotakeout.http.HttpUtil;
import zykj.com.barguotakeout.http.SimpleHttpHandler;
import zykj.com.barguotakeout.http.UrlContants;
import zykj.com.barguotakeout.model.BaGuoRank;
import zykj.com.barguotakeout.model.Goods;
import zykj.com.barguotakeout.model.GoodsModel;
import zykj.com.barguotakeout.model.OrderPaper;

/**
 * Created by csh on 15-4-28.
 */
public class RanksAdapter extends BaseAdapter {
    private List<BaGuoRank> list;
    private LayoutInflater inflater;
    private Context context;
    private static final String TAG = RanksAdapter.class.getSimpleName();

    public RanksAdapter(Context context, List<BaGuoRank> list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BaGuoRank getItem(int position) {return list.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView=inflater.inflate(R.layout.item_ranks,null);
            holder=new ViewHolder();
            holder.iv_imageUrl= (ImageView) convertView.findViewById(R.id.iv_imageUrl);//图片
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);//标题
            holder.tv_identificate= (TextView) convertView.findViewById(R.id.tv_identificate);//认证
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);//地址
            holder.author_star= (ImageView) convertView.findViewById(R.id.author_star);//大拇指
            holder.tv_starnum= (TextView) convertView.findViewById(R.id.tv_starnum);//点赞人数
            holder.tv_rankdetail= (ImageView) convertView.findViewById(R.id.tv_rankdetail);//详情
            holder.button_share= (LinearLayout) convertView.findViewById(R.id.button_share);//分享
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        final BaGuoRank model = list.get(position);

        ImageUtil.displayImage2Circle(holder.iv_imageUrl, UrlContants.getUrl(model.getReslogo()), 10f, null);//图片
        holder.tv_title.setText(model.getResname());//标题
        holder.tv_starnum.setText(String.format("(%s)喜欢",model.getStarnum()));//点赞

        //if(Integer.valueOf(model.getIdenficate())>8f){
        if(position == 0){
            holder.tv_identificate.setVisibility(View.VISIBLE);//显示认证
        }
        if(model.getIsstarted() == 1){
            holder.author_star.setImageResource(R.mipmap.like_icon_light);
            holder.tv_starnum.setTextColor(R.color.red);
        }else{
            if(Mapplication.getModel().getUserid() != null){
                holder.tv_starnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestParams params = new RequestParams();
                        params.add("articleid",model.getArticleid());
                        params.add("userid", Mapplication.getModel().getUserid());
                        HttpUtil.commitbaguostar(new SimpleHttpHandler() {
                            @Override
                            public void onJsonSuccess(JSONObject json) {
                                holder.author_star.setImageResource(R.mipmap.like_icon_light);
                                holder.tv_starnum.setTextColor(R.color.red);
                                holder.tv_starnum.setText(String.format("(%s)喜欢",model.getStarnum()+1));
                                holder.tv_starnum.setOnClickListener(null);
                                ToastUTil.shortT(context,"点赞成功");
                            }
                        },params);
                    }
                });
            }
        }

        holder.button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUTil.shortT(context,"分享成功");
            }
        });
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_imageUrl;//图片
        TextView tv_title;//标题
        TextView tv_identificate;//认证
        TextView tv_address; //地址
        ImageView author_star;//大拇指
        TextView tv_starnum;//多少人点赞
        ImageView tv_rankdetail;//详情
        LinearLayout button_share;//分享
    }
}
