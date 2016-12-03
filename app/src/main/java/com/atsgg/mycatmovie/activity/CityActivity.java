package com.atsgg.mycatmovie.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.city.CityBean;
import com.atsgg.mycatmovie.common.BaseActivity;
import com.atsgg.mycatmovie.fragment.CityFragment;
import com.atsgg.mycatmovie.utils.CacheUtils;
import com.atsgg.mycatmovie.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class CityActivity extends BaseActivity implements CityFragment.OnCitySelectListener {

    @BindView(R.id.iv_city_back)
    ImageView ivCityBack;
    @BindView(R.id.fl_city)
    FrameLayout flCity;

    private CityFragment mCityFragment;
    private LocalBroadcastManager mBroadcastManager;


    private String mCity;

    @Override
    protected void initData() {


        mBroadcastManager = LocalBroadcastManager.getInstance(this);

        mCityFragment = new CityFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_city, mCityFragment, CityFragment.class.getSimpleName()).commit();

        mCityFragment.setOnCitySelectListener(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_city;
    }


    @OnClick(R.id.iv_city_back)
    public void onClick() {
        removeCurrentActivity();
    }


    @Override
    public void select(CityBean cityBean) {
        // 得到选择的city
        mCity = cityBean.getCity();
        // 放入sp中
        CacheUtils.putString(CityActivity.this, Constants.CITY, mCity);
        mBroadcastManager.sendBroadcast(new Intent(Constants.CITY_CHANGE));
        removeCurrentActivity();
    }



}
