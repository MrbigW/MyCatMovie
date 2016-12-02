package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.RecentBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/2.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */
public class RecentAdapter extends CommonAdapter<RecentBean.DataBean.MoviesBean> {

    private Context mContext;
    private int mLayoutId;

    public RecentAdapter(Context context, int layoutId, List<RecentBean.DataBean.MoviesBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RencentHolder(mContext, View.inflate(mContext, mLayoutId, null));
    }

    @Override
    public void convert(ViewHolder holder, RecentBean.DataBean.MoviesBean moviesBean) {
        RencentHolder rencentHolder = (RencentHolder) holder;

        String imgUrl = moviesBean.getImg().replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL);
        Glide.with(mContext).load(imgUrl).into(rencentHolder.iv_recent_icon);

        rencentHolder.tv_date.setText(moviesBean.getRt());
//        else if (result[0].endsWith("17")) {
//            rencentHolder.tv_date.setText("17年" + result[1] + "月" + result[2] + "日");
//        }


        rencentHolder.tv_rencent_name.setText(moviesBean.getNm());
        rencentHolder.tv_rencent_wish.setText(moviesBean.getWish() + "人想看");
    }

    class RencentHolder extends ViewHolder {

        private TextView tv_date;
        private ImageView iv_recent_icon;
        private ImageView iv_like;
        private TextView tv_rencent_name;
        private TextView tv_rencent_wish;

        public RencentHolder(Context context, View itemView) {
            super(context, itemView);

            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_recent_icon = (ImageView) itemView.findViewById(R.id.iv_recent_icon);
            iv_like = (ImageView) itemView.findViewById(R.id.iv_like);
            tv_rencent_name = (TextView) itemView.findViewById(R.id.tv_rencent_name);
            tv_rencent_wish = (TextView) itemView.findViewById(R.id.tv_rencent_wish);

        }
    }

}
