package zykj.com.barguotakeout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zykj.com.barguotakeout.R;
import zykj.com.barguotakeout.model.MyAddressModel;
import zykj.com.barguotakeout.model.ResturantModel;

/**
 * Created by Administrator on 2015/5/16.
 */
public class MyAddressAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<MyAddressModel> list;


    public MyAddressAdapter(Context context,List<MyAddressModel> list) {
        inflater=LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        MyAddressModel model = list.get(i);
        if (view == null){
            view = inflater.inflate(R.layout.myadress_item,null);
            holder = new ViewHolder();
            holder.tv_myadress_name = (TextView)view.findViewById(R.id.tv_myadress_name);
            holder.tv_myadress_fhone = (TextView)view.findViewById(R.id.tv_myadress_fhone);
            holder.tv_adress_adress = (TextView)view.findViewById(R.id.tv_adress_adress);
            holder.im_myadress_choose = (ImageView)view.findViewById(R.id.im_myadress_choose);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        holder.tv_myadress_name.setText(model.getRealname());
        holder.tv_myadress_fhone.setText(model.getPhonenum());
        holder.tv_adress_adress.setText(model.getAddress());
        holder.im_myadress_choose.setVisibility(view.VISIBLE);

        return view;
    }

    static class ViewHolder{
        TextView tv_myadress_name;
        TextView tv_myadress_fhone;
        TextView tv_adress_adress;
        ImageView im_myadress_choose;
    }
}
