package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.movie.find.FindGridViewBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrbigW on 2016/12/3.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class FindGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<FindGridViewBean.DataBean> mDataBeen;

    public FindGridAdapter(Context context, List<FindGridViewBean.DataBean> gridViewBean) {
        this.mContext = context;
        this.mDataBeen = gridViewBean;
    }

    @Override
    public int getCount() {
        return mDataBeen == null ? 0 : mDataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_find_grid_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FindGridViewBean.DataBean bean = mDataBeen.get(position);
        viewHolder.tvFindTitle.setText(bean.getBoardName());
        switch (position) {
            case 0:
                viewHolder.tvFindTitle.setTextColor(Color.parseColor("#ff4285f4"));
                break;
            case 1:
                viewHolder.tvFindTitle.setTextColor(Color.parseColor("#ffe91e63"));
                break;
            case 2:
                viewHolder.tvFindTitle.setTextColor(Color.parseColor("#ffff5722"));
                break;
            case 3:
                viewHolder.tvFindTitle.setTextColor(Color.parseColor("#ff8bc34a"));
                break;
        }

        viewHolder.tvFinName.setText(bean.getMovieName());

        Glide.with(mContext)
                .load(bean.getMovieImgs().get(0).replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL))
                .into(viewHolder.ivFindTop);
        Glide.with(mContext)
                .load(bean.getMovieImgs().get(1).replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL))
                .into(viewHolder.ivFindDown);


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_find_title)
        TextView tvFindTitle;
        @BindView(R.id.tv_fin_name)
        TextView tvFinName;
        @BindView(R.id.iv_find_down)
        ImageView ivFindDown;
        @BindView(R.id.iv_find_top)
        ImageView ivFindTop;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}













