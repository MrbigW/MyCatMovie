package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.graphics.Bitmap;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.movie.find.FindRecyclerBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/3.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class FindRecyclerAdapter extends CommonAdapter<FindRecyclerBean.DataBean> {

    public FindRecyclerAdapter(Context context, int layoutId, List<FindRecyclerBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(final ViewHolder holder, FindRecyclerBean.DataBean dataBean) {
        holder.setText(R.id.tv_awards_name, dataBean.getFestivalName());
        holder.setText(R.id.tv_awards_movie, dataBean.getMovieName());
        holder.setText(R.id.tv_awards_prize, dataBean.getPrizeName());
        holder.setText(R.id.tv_awards_data, dataBean.getHeldDate().substring(5, dataBean.getHeldDate().length()));

        Glide.with(mContext).load(dataBean.getImg().replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL))
                .asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.iv_awards_icon, resource);
            }
        });

    }
}
