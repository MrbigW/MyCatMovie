package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.movie.next.MovieNextBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class MovieNextAdapter extends CommonAdapter<MovieNextBean.DataBean.ComingBean> {

    private Context mContext;
    private int layoutId;

    public MovieNextAdapter(Context context, int layoutId, List<MovieNextBean.DataBean.ComingBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.layoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieNextHolder(mContext, View.inflate(mContext, layoutId, null));
    }

    @Override
    public void convert(ViewHolder holder, final MovieNextBean.DataBean.ComingBean comingBean) {
        MovieNextHolder nextHolder = (MovieNextHolder) holder;

        String imgUrl = comingBean.getImg().replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL);
        Glide.with(mContext).load(imgUrl).into(nextHolder.iv_next_icon);

        nextHolder.tv_next_name.setText(comingBean.getNm());
        nextHolder.tv_next_audience_num.setText(comingBean.getSc() + "");
        nextHolder.tv_next_profession_num.setText(comingBean.getProScore() + "");
        nextHolder.tv_next_src.setText(comingBean.getScm() + "");
        nextHolder.tv_next_showinfo.setText(comingBean.getDesc() + "");
        nextHolder.iv_next_type.setTag(comingBean.getId());

        if (comingBean.getProScore() == 0.0 && comingBean.getSc() != 0.0) {
            nextHolder.v_next_line.setVisibility(View.GONE);
            nextHolder.tv_next_profession.setVisibility(View.GONE);
            nextHolder.tv_next_profession_num.setVisibility(View.GONE);
        }


        if (comingBean.getProScore() != 0.0 && comingBean.getSc() == 0.0) {
            nextHolder.v_next_line.setVisibility(View.GONE);
            nextHolder.tv_next_audience_num.setVisibility(View.GONE);
            nextHolder.tv_next_audience.setVisibility(View.GONE);
        }


        if (comingBean.getSc() == 0.0 && comingBean.getProScore() == 0.0) {
            nextHolder.tv_next_audience_num.setVisibility(View.GONE);
            nextHolder.tv_next_audience.setVisibility(View.GONE);
            nextHolder.tv_next_profession.setVisibility(View.GONE);
            nextHolder.tv_next_profession_num.setVisibility(View.GONE);
            nextHolder.v_next_line.setVisibility(View.GONE);
            nextHolder.tv_next_wish_num.setVisibility(View.VISIBLE);
            nextHolder.tv_next_wish.setVisibility(View.VISIBLE);
            nextHolder.tv_next_wish_num.setText(comingBean.getWish() + "");
        }


        if (comingBean.getVer().contains("3D")) {
            if (comingBean.getVer().replaceAll("3D", "").contains("IMAX")) {
                if (nextHolder.iv_next_type.getTag().toString().equals(comingBean.getId() + "")) {
                    nextHolder.iv_next_type.setImageResource(R.drawable.w1);
                }
            } else {
                if (nextHolder.iv_next_type.getTag().toString().equals(comingBean.getId() + "")) {
                    nextHolder.iv_next_type.setImageResource(R.drawable.w0);
                }
            }
        }else if(comingBean.getVer().contains("2D")) {
            if (comingBean.getVer().replaceAll("2d", "").contains("IMAX")) {
                if (nextHolder.iv_next_type.getTag().toString().equals(comingBean.getId() + "")) {
                    nextHolder.iv_next_type.setImageResource(R.drawable.vz);
                }
            } else {
                if (nextHolder.iv_next_type.getTag().toString().equals(comingBean.getId() + "")) {
                    nextHolder.iv_next_type.setVisibility(View.GONE);
                }
            }
        }else {
            if (nextHolder.iv_next_type.getTag().toString().equals(comingBean.getId() + "")) {
                nextHolder.iv_next_type.setVisibility(View.GONE);
            }
        }


    }

    class MovieNextHolder extends ViewHolder {

        private ImageView iv_next_icon;
        private ImageView iv_next_play;
        private TextView tv_next_name;
        private ImageView iv_next_type;
        private TextView tv_next_audience;
        private TextView tv_next_audience_num;
        private View v_next_line;
        private TextView tv_next_profession;
        private TextView tv_next_profession_num;
        private TextView tv_next_wish_num;
        private TextView tv_next_wish;
        private TextView tv_next_src;
        private TextView tv_next_showinfo;
        private TextView tv_next_buy;

        public MovieNextHolder(Context context, View itemView) {
            super(context, itemView);
            iv_next_icon = (ImageView) itemView.findViewById(R.id.iv_next_icon);
            iv_next_play = (ImageView) itemView.findViewById(R.id.iv_next_play);
            tv_next_name = (TextView) itemView.findViewById(R.id.tv_next_name);
            iv_next_type = (ImageView) itemView.findViewById(R.id.iv_next_type);
            tv_next_audience = (TextView) itemView.findViewById(R.id.tv_next_audience);
            tv_next_audience_num = (TextView) itemView.findViewById(R.id.tv_next_audience_num);
            v_next_line = itemView.findViewById(R.id.v_next_line);
            tv_next_profession = (TextView) itemView.findViewById(R.id.tv_next_profession);
            tv_next_profession_num = (TextView) itemView.findViewById(R.id.tv_next_profession_num);
            tv_next_wish_num = (TextView) itemView.findViewById(R.id.tv_next_wish_num);
            tv_next_wish = (TextView) itemView.findViewById(R.id.tv_hot_wish);
            tv_next_src = (TextView) itemView.findViewById(R.id.tv_hot_src);
            tv_next_showinfo = (TextView) itemView.findViewById(R.id.tv_next_actor);
            tv_next_buy = (TextView) itemView.findViewById(R.id.tv_hot_buy);
        }
    }

}