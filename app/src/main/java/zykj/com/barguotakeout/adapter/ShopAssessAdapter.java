package zykj.com.barguotakeout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.model.JokeEntity;
import zykj.com.barguotakeout.model.ShopAssess;


/**
 * Created by ss on 15-4-17.
 */
public class ShopAssessAdapter extends CommonAdapter {


    public ShopAssessAdapter(Context context, List<ShopAssess> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView== null){
            convertView = inflater.inflate(R.layout.item_assess, null);
            holder = new ViewHolder();
            holder.assess_ratingBar= (RatingBar) convertView.findViewById(R.id.assess_ratingBar);
            holder.assess_content= (TextView) convertView.findViewById(R.id.assess_content);
            holder.assess_phone= (TextView) convertView.findViewById(R.id.assess_phone);
            holder.assess_createtime= (TextView) convertView.findViewById(R.id.assess_createtime);
            holder.order_detail= (TextView) convertView.findViewById(R.id.order_detail);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ShopAssess assess = (ShopAssess)list.get(position);
        holder.assess_ratingBar.setNumStars(Integer.valueOf(assess.getStarnum()));
        holder.assess_content.setText(assess.getContent());
        holder.assess_phone.setText(assess.getUsername());
        holder.assess_createtime.setText(assess.getUpdatetime());
        holder.order_detail.setText(assess.getOrdernum());
        return  convertView;
    }

    class ViewHolder{
        RatingBar assess_ratingBar;
        TextView assess_content;
        TextView assess_phone;
        TextView assess_createtime;
        TextView order_detail;
    }
}
