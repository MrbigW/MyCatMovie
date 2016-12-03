package com.atsgg.mycatmovie.adapter.movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.movie.hot.MovieHHotBean;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class HotMovieAdapter extends BaseAdapter {

    private Context mContext;
    private List<MovieHHotBean.DataBean.HotBean> mHotBeen;

    public HotMovieAdapter(Context context, List<MovieHHotBean.DataBean.HotBean> movies) {
        this.mContext = context;
        this.mHotBeen = movies;
    }

    @Override
    public int getCount() {
        return mHotBeen == null ? 0 : mHotBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_movie_hot, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MovieHHotBean.DataBean.HotBean moviesBean = mHotBeen.get(position);

        viewHolder.tvHotName.setText(moviesBean.getNm() + "");
        viewHolder.tvHotAudienceNum.setText(moviesBean.getSc() + "");
        viewHolder.tvHotProfessionNum.setText(moviesBean.getProScore() + "");
        viewHolder.tvHotSrc.setText(moviesBean.getScm() + "");
        viewHolder.tvHotShowinfo.setText(moviesBean.getShowInfo() + "");
        viewHolder.ivHotType.setTag(moviesBean.getId());

        if (moviesBean.getSc() != 0 && moviesBean.getProScore() == 0) {
            viewHolder.tvHotAudienceNum.setVisibility(View.VISIBLE);
            viewHolder.tvHotAudience.setVisibility(View.VISIBLE);
            viewHolder.tvHotProfession.setVisibility(View.GONE);
            viewHolder.tvHotProfessionNum.setVisibility(View.GONE);
            viewHolder.vHotLine.setVisibility(View.GONE);
            viewHolder.tvHotWish.setVisibility(View.GONE);
            viewHolder.tvHotWishNum.setVisibility(View.GONE);
        }
        if (moviesBean.getSc() == 0 && moviesBean.getProScore() != 0) {
            viewHolder.tvHotAudienceNum.setVisibility(View.GONE);
            viewHolder.tvHotAudience.setVisibility(View.GONE);
            viewHolder.tvHotProfession.setVisibility(View.VISIBLE);
            viewHolder.tvHotProfessionNum.setVisibility(View.VISIBLE);
            viewHolder.vHotLine.setVisibility(View.GONE);
            viewHolder.tvHotWish.setVisibility(View.GONE);
            viewHolder.tvHotWishNum.setVisibility(View.GONE);
        }

        if (moviesBean.getSc() == 0 && moviesBean.getProScore() == 0) {
            viewHolder.tvHotProfession.setVisibility(View.GONE);
            viewHolder.tvHotProfessionNum.setVisibility(View.GONE);
            viewHolder.tvHotAudienceNum.setVisibility(View.GONE);
            viewHolder.tvHotAudience.setVisibility(View.GONE);
            viewHolder.vHotLine.setVisibility(View.GONE);
            viewHolder.tvHotWish.setVisibility(View.VISIBLE);
            viewHolder.tvHotWishNum.setVisibility(View.VISIBLE);
            viewHolder.tvHotWishNum.setText(moviesBean.getWish() + "");
        }


        Glide.with(mContext)
                .load(moviesBean.getImg().replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL))
                .into(viewHolder.ivHotIcon);

        viewHolder.tvHotAudienceNum.setText(moviesBean.getSc() + "");

        int preSale = moviesBean.getPreSale();
        switch (preSale) {
            case 1:
                viewHolder.tvHotBuy.setBackgroundResource(R.drawable.tv_presale_bg);
                viewHolder.tvHotBuy.setTextColor(UIUtils.getColor(R.color.presale));
                viewHolder.tvHotBuy.setText("预购");
                break;
            case 0:
                viewHolder.tvHotBuy.setBackgroundResource(R.drawable.tv_buy_bg);
                viewHolder.tvHotBuy.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        }


        if (moviesBean.getVer().contains("3D")) {
            if (moviesBean.getVer().replaceAll("3D", "").contains("IMAX")) {
                if (viewHolder.ivHotType.getTag().toString().equals(moviesBean.getId() + "")) {
                    viewHolder.ivHotType.setImageResource(R.drawable.w1);
                }
            } else {
                if (viewHolder.ivHotType.getTag().toString().equals(moviesBean.getId() + "")) {
                    viewHolder.ivHotType.setImageResource(R.drawable.w0);
                }
            }
        }else if(moviesBean.getVer().contains("2D")) {
            if (moviesBean.getVer().replaceAll("2d", "").contains("IMAX")) {
                if (viewHolder.ivHotType.getTag().toString().equals(moviesBean.getId() + "")) {
                    viewHolder.ivHotType.setImageResource(R.drawable.vz);
                }
            } else {
                if (viewHolder.ivHotType.getTag().toString().equals(moviesBean.getId() + "")) {
                    viewHolder.ivHotType.setVisibility(View.GONE);
                }
            }
        }else {
            if (viewHolder.ivHotType.getTag().toString().equals(moviesBean.getId() + "")) {
                viewHolder.ivHotType.setVisibility(View.GONE);
            }
        }

        return convertView;

    }


    static class ViewHolder {
        @BindView(R.id.iv_hot_icon)
        ImageView ivHotIcon;
        @BindView(R.id.iv_hot_play)
        ImageView ivHotPlay;
        @BindView(R.id.tv_hot_name)
        TextView tvHotName;
        @BindView(R.id.iv_hot_type)
        ImageView ivHotType;
        @BindView(R.id.tv_hot_audience)
        TextView tvHotAudience;
        @BindView(R.id.tv_hot_audience_num)
        TextView tvHotAudienceNum;
        @BindView(R.id.v_hot_line)
        View vHotLine;
        @BindView(R.id.tv_hot_profession)
        TextView tvHotProfession;
        @BindView(R.id.tv_hot_profession_num)
        TextView tvHotProfessionNum;
        @BindView(R.id.tv_hot_wish_num)
        TextView tvHotWishNum;
        @BindView(R.id.tv_hot_wish)
        TextView tvHotWish;
        @BindView(R.id.tv_hot_src)
        TextView tvHotSrc;
        @BindView(R.id.tv_hot_showinfo)
        TextView tvHotShowinfo;
        @BindView(R.id.tv_hot_buy)
        TextView tvHotBuy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}







