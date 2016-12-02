package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.PrevueBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/2.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */
public class PrevueAdapter extends CommonAdapter<PrevueBean.DataBean> {

    private Context mContext;
    private int mLayoutId;

    public PrevueAdapter(Context context, int layoutId, List<PrevueBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PrevueViewHolder(mContext, View.inflate(mContext, mLayoutId, null));
    }

    @Override
    public void convert(ViewHolder holder, PrevueBean.DataBean dataBean) {
        PrevueViewHolder viewHolder = (PrevueViewHolder) holder;

        String imgUrl = dataBean.getImg();
        Glide.with(mContext).load(imgUrl).into(viewHolder.prevue_icon);

        viewHolder.tv_name.setText(dataBean.getMovieName());
        viewHolder.tv_dsc.setText(dataBean.getOriginName());
    }

    class PrevueViewHolder extends ViewHolder {

        private ImageView prevue_icon;
        private ImageView prevue_play;
        private TextView tv_name;
        private TextView tv_dsc;


        public PrevueViewHolder(Context context, View itemView) {
            super(context, itemView);
            prevue_icon = (ImageView) itemView.findViewById(R.id.prevue_icon);
            prevue_play = (ImageView) itemView.findViewById(R.id.prevue_play);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_dsc = (TextView) itemView.findViewById(R.id.tv_dsc);
        }
    }


}
