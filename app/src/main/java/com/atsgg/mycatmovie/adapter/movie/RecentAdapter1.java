package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.graphics.Bitmap;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.movie.next.RecentBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/2.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */
public class RecentAdapter1 extends CommonAdapter<RecentBean.DataBean.MoviesBean> {


    public RecentAdapter1(Context context, int layoutId, List<RecentBean.DataBean.MoviesBean> datas) {
        super(context, layoutId, datas);

    }

    @Override
    public void convert(final ViewHolder holder, RecentBean.DataBean.MoviesBean moviesBean) {

        String imgUrl = moviesBean.getImg().replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL);
        Glide.with(mContext).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.iv_recent_icon, resource);
            }
        });

        holder.setText(R.id.tv_date, moviesBean.getRt());

        holder.setText(R.id.tv_rencent_name, moviesBean.getNm());
        holder.setText(R.id.tv_rencent_wish, moviesBean.getWish() + "人想看");

    }

}
