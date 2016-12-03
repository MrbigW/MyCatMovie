package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.graphics.Bitmap;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.movie.next.PrevueBean;
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
public class PrevueAdapter1 extends CommonAdapter<PrevueBean.DataBean> {

    public PrevueAdapter1(Context context, int layoutId, List<PrevueBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(final ViewHolder holder, PrevueBean.DataBean dataBean) {

        String imgUrl = dataBean.getImg();
        Glide.with(mContext).load(imgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.prevue_icon, resource);
            }
        });

        holder.setText(R.id.tv_name, dataBean.getMovieName());
        holder.setText(R.id.tv_dsc, dataBean.getOriginName());

    }

}
