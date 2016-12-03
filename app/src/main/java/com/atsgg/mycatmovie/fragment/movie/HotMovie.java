package com.atsgg.mycatmovie.fragment.movie;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.movie.HotMovieAdapter;
import com.atsgg.mycatmovie.bean.movie.hot.MovieHHotBean;
import com.atsgg.mycatmovie.bean.movie.hot.MovieHotTop;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.ui.MovieListView;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.DownLoaderUtils;
import com.atsgg.mycatmovie.utils.ToastUtil;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class HotMovie extends BaseFragment {

    @BindView(R.id.lv_movie_hotmovie)
    MovieListView lvMovieHotmovie;

//    private MovieHotBean mMovieHotBean;

//    private MovieHotBean.DataBean.MoviesBean moviesBean;

    private List<MovieHHotBean.DataBean.HotBean> mHotBean;

    private MovieHotTop mHotTop;

    private View mHot_header;

    private Banner bannerHotmovie;
    private ImageView ivHotIcon;
    private ImageView ivHotPlay;
    private TextView tvHotName;
    private ImageView ivHotType;
    private TextView tvHotAudience;
    private TextView tvHotAudienceNum;
    private View vHotLine;
    private TextView tvHotProfession;
    private TextView tvHotProfessionNum;
    private TextView tvHotWishNum;
    private TextView tvHotWish;
    private TextView tvHotSrc;
    private TextView tvHotShowinfo;
    private TextView tvHotBuy;
    private TextView tvHotZhuanti1;
    private TextView tvHotZhuanti2;

    @Override
    protected String getUrl() {
        return Constants.URL_MOVIE_HHOT;
    }

    @Override
    protected void initData(String content) {
        processData(content);
        mHot_header = View.inflate(getActivity(), R.layout.hotmovie_header, null);
        findViews();
        setDataToViews();
        lvMovieHotmovie.addHeaderView(mHot_header);
        lvMovieHotmovie.setAdapter(new HotMovieAdapter(getActivity(), mHotBean.subList(1, mHotBean.size())));
        lvMovieHotmovie.setRefreshInterface(new MovieListView.IreflashListener() {
            @Override
            public void onReflash() {
                UIUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lvMovieHotmovie.reflashComplete();
                    }
                }, 2000);
            }
        });
    }


    @Override
    protected void initFiled() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hotmovie;
    }

    private void processData(String content) {

        mHotBean = JSON.parseObject(content, MovieHHotBean.class).getData().getHot();

    }


    private void findViews() {
        bannerHotmovie = (Banner) mHot_header.findViewById(R.id.banner_hotmovie);
        ivHotIcon = (ImageView) mHot_header.findViewById(R.id.iv_hot_icon);
        ivHotPlay = (ImageView) mHot_header.findViewById(R.id.iv_hot_play);
        tvHotName = (TextView) mHot_header.findViewById(R.id.tv_hot_name);
        ivHotType = (ImageView) mHot_header.findViewById(R.id.iv_hot_type);
        tvHotAudience = (TextView) mHot_header.findViewById(R.id.tv_hot_audience);
        tvHotAudienceNum = (TextView) mHot_header.findViewById(R.id.tv_hot_audience_num);
        vHotLine = mHot_header.findViewById(R.id.v_hot_line);
        tvHotProfession = (TextView) mHot_header.findViewById(R.id.tv_hot_profession);
        tvHotProfessionNum = (TextView) mHot_header.findViewById(R.id.tv_hot_profession_num);
        tvHotWishNum = (TextView) mHot_header.findViewById(R.id.tv_hot_wish_num);
        tvHotWish = (TextView) mHot_header.findViewById(R.id.tv_hot_wish);
        tvHotSrc = (TextView) mHot_header.findViewById(R.id.tv_hot_src);
        tvHotShowinfo = (TextView) mHot_header.findViewById(R.id.tv_hot_showinfo);
        tvHotBuy = (TextView) mHot_header.findViewById(R.id.tv_hot_buy);
        tvHotZhuanti1 = (TextView) mHot_header.findViewById(R.id.tv_hot_zhuanti1);
        tvHotZhuanti2 = (TextView) mHot_header.findViewById(R.id.tv_hot_zhuanti2);
    }

    private void setDataToViews() {
        setBanner();
    }

    private void setHead() {

        tvHotName.setText(mHotBean.get(0).getNm() + "");
        tvHotAudienceNum.setText(mHotBean.get(0).getSc() + "");
        tvHotProfessionNum.setText(mHotBean.get(0).getProScore() + "");
        tvHotSrc.setText(mHotBean.get(0).getScm() + "");
        tvHotShowinfo.setText(mHotBean.get(0).getShowInfo() + "");
        tvHotZhuanti1.setText(mHotBean.get(0).getNewsHeadlines().get(0).getTitle());
        tvHotZhuanti2.setText(mHotBean.get(0).getNewsHeadlines().get(1).getTitle());
        ivHotType.setTag(mHotBean.get(0).getId());


        if (mHotBean.get(0).getSc() != 0 && mHotBean.get(0).getProScore() == 0) {
            tvHotAudienceNum.setVisibility(View.VISIBLE);
            tvHotAudience.setVisibility(View.VISIBLE);
            tvHotProfession.setVisibility(View.GONE);
            tvHotProfessionNum.setVisibility(View.GONE);
            vHotLine.setVisibility(View.GONE);
            tvHotWish.setVisibility(View.GONE);
            tvHotWishNum.setVisibility(View.GONE);
        }
        if (mHotBean.get(0).getSc() == 0 && mHotBean.get(0).getProScore() != 0) {
            tvHotAudienceNum.setVisibility(View.GONE);
            tvHotAudience.setVisibility(View.GONE);
            tvHotProfession.setVisibility(View.VISIBLE);
            tvHotProfessionNum.setVisibility(View.VISIBLE);
            vHotLine.setVisibility(View.GONE);
            tvHotWish.setVisibility(View.GONE);
            tvHotWishNum.setVisibility(View.GONE);
        }

        if (mHotBean.get(0).getSc() == 0 && mHotBean.get(0).getProScore() == 0) {
            tvHotProfession.setVisibility(View.GONE);
            tvHotProfessionNum.setVisibility(View.GONE);
            tvHotAudienceNum.setVisibility(View.GONE);
            tvHotAudience.setVisibility(View.GONE);
            vHotLine.setVisibility(View.GONE);
            tvHotWish.setVisibility(View.VISIBLE);
            tvHotWishNum.setVisibility(View.VISIBLE);
            tvHotWishNum.setText(mHotBean.get(0).getWish() + "");
        }

        if (mHotBean.get(0).getVer().contains("3D")) {
            if (mHotBean.get(0).getVer().replaceAll("3D", "").contains("IMAX")) {
                if (ivHotType.getTag().toString().equals(mHotBean.get(0).getId() + "")) {
                    ivHotType.setImageResource(R.drawable.w1);
                }
            } else {
                if (ivHotType.getTag().toString().equals(mHotBean.get(0).getId() + "")) {
                    ivHotType.setImageResource(R.drawable.w0);
                }
            }
        } else if (mHotBean.get(0).getVer().contains("2D")) {
            if (mHotBean.get(0).getVer().replaceAll("2d", "").contains("IMAX")) {
                if (ivHotType.getTag().toString().equals(mHotBean.get(0).getId() + "")) {
                    ivHotType.setImageResource(R.drawable.vz);
                }
            } else {
                if (ivHotType.getTag().toString().equals(mHotBean.get(0).getId() + "")) {
                    ivHotType.setVisibility(View.GONE);
                }
            }
        } else {
            if (ivHotType.getTag().toString().equals(mHotBean.get(0).getId() + "")) {
                ivHotType.setVisibility(View.GONE);
            }
        }


        Glide.with(getActivity())
                .load(mHotBean.get(0).getImg().replaceAll(Constants.ERROR_URL_2, Constants.RIGHT_URL).replaceAll(Constants.ERROR_URL, Constants.RIGHT_URL))
                .into(ivHotIcon);


        int preSale = mHotBean.get(0).getPreSale();
        switch (preSale) {
            case 1:
                tvHotBuy.setBackgroundResource(R.drawable.tv_presale_bg);
                tvHotBuy.setTextColor(UIUtils.getColor(R.color.presale));
                tvHotBuy.setText("预购");
                break;
            case 0:
                tvHotBuy.setBackgroundResource(R.drawable.tv_buy_bg);
                tvHotBuy.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        }

    }

    private void setBanner() {
        new DownLoaderUtils().getJsonResult(Constants.URL_MOVIE_HOT_TOP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        setBannerData();
                        setHead();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        processTopJson(s);
                    }
                });
    }

    private void setBannerData() {
        bannerHotmovie.setBannerStyle(BannerConfig.NOT_INDICATOR);
        // 设置图片加载器
        bannerHotmovie.setImageLoader(new GlideImageLoader());
        // 设置图片集合
        List<String> imageUris = new ArrayList<>();
        for (int i = 0; i < mHotTop.getData().size(); i++) {
            imageUris.add(mHotTop.getData().get(i).getImgUrl());
        }
        bannerHotmovie.setImages(imageUris);
        // 设置banner动画效果
        bannerHotmovie.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        bannerHotmovie.isAutoPlay(true);
        //设置轮播时间
        bannerHotmovie.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        bannerHotmovie.start();
        //banner的点击事件
        bannerHotmovie.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showToast(getActivity(), position + "");
            }
        });
    }


    private void processTopJson(String s) {
        mHotTop = JSON.parseObject(s, MovieHotTop.class);
    }

    /**
     * banner的图片加载器
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Glide.with(context).load((String) path).into(imageView);

        }
    }
}




