package com.atsgg.mycatmovie.fragment.movie;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.movie.HotMovieAdapter;
import com.atsgg.mycatmovie.bean.MovieHotBean;
import com.atsgg.mycatmovie.bean.MovieHotTop;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.atsgg.mycatmovie.common.CatMovieApplication.mContext;

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

    private MovieHotBean mMovieHotBean;

    private MovieHotBean.DataBean.MoviesBean moviesBean;

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
        return Constants.URL_MOVIE_HOT;
    }

    @Override
    protected void initData(String content) {
        processData(content);
        mHot_header = View.inflate(getActivity(), R.layout.hotmovie_header, null);
        findViews();
        setDataToViews();
        lvMovieHotmovie.addHeaderView(mHot_header);
        lvMovieHotmovie.setAdapter(new HotMovieAdapter(getActivity(), mMovieHotBean.getData().getMovies().subList(1, mMovieHotBean.getData().getMovies().size())));
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

        mMovieHotBean = JSON.parseObject(content, MovieHotBean.class);
        try {
            JSONObject object = new JSONObject(content);
            JSONObject data = object.getJSONObject("data");
            JSONArray movies = data.getJSONArray("movies");
            for (int i = 0; i < movies.length(); i++) {
                mMovieHotBean.getData().getMovies().get(i).setIs3d(movies.getJSONObject(i).getBoolean("3d"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        moviesBean = mMovieHotBean.getData().getMovies().get(0);
        tvHotName.setText(moviesBean.getNm() + "");
        tvHotAudienceNum.setText(moviesBean.getSc() + "");
        tvHotSrc.setText(moviesBean.getScm() + "");
        tvHotShowinfo.setText(moviesBean.getShowInfo() + "");

        if (moviesBean.getSc() == 0) {
            tvHotAudienceNum.setVisibility(View.GONE);
            tvHotAudience.setVisibility(View.GONE);
            vHotLine.setVisibility(View.GONE);
            tvHotWish.setVisibility(View.VISIBLE);
            tvHotWishNum.setVisibility(View.VISIBLE);
            tvHotWishNum.setText(moviesBean.getWish() + "");
        }

        if (moviesBean.is3d()) {
            ivHotType.setVisibility(View.VISIBLE);
            if (moviesBean.isImax()) {
                ivHotType.setImageResource(R.drawable.w1);
            } else {
                ivHotType.setImageResource(R.drawable.w0);

            }
        } else {
            ivHotType.setVisibility(View.VISIBLE);
            if (moviesBean.isImax()) {
                ivHotType.setImageResource(R.drawable.vz);
            } else {
                ivHotType.setVisibility(View.GONE);
            }
        }

        Glide.with(mContext)
                .load(moviesBean.getImg())
                .into(ivHotIcon);

        tvHotAudienceNum.setText(moviesBean.getSc() + "");

        int preSale = moviesBean.getPreSale();
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

        tvHotProfession.setVisibility(View.GONE);
        tvHotProfessionNum.setVisibility(View.GONE);
        vHotLine.setVisibility(View.GONE);
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




