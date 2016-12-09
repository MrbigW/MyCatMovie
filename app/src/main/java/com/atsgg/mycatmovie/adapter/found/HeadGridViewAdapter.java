package com.atsgg.mycatmovie.adapter.found;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.found.HeadBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 发现页面--头部分--GirdView的适配器
 */
public class HeadGridViewAdapter extends BaseAdapter {
    private final Context context;
    private List<HeadBean.DataBean> datas = new ArrayList<>();

    public HeadGridViewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holdler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gridview, null);
            holdler = new ViewHolder(convertView);
            convertView.setTag(holdler);
        } else {
            holdler = (ViewHolder) convertView.getTag();
        }
        HeadBean.DataBean dataBean = datas.get(position);
        holdler.headTvName.setText(dataBean.getTitle());
        Glide.with(context).load(dataBean.getImage().getUrl()).into(holdler.headIvIcon);
        return convertView;
    }

    public void refresh(List<HeadBean.DataBean> datas) {
        if (datas != null && datas.size() > 0) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }


    static class ViewHolder {
        @BindView(R.id.head_iv_icon)
        ImageView headIvIcon;
        @BindView(R.id.head_tv_name)
        TextView headTvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
