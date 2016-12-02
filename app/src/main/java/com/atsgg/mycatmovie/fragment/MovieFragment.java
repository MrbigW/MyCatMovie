package com.atsgg.mycatmovie.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.activity.CityActivity;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.fragment.movie.FindMovie;
import com.atsgg.mycatmovie.fragment.movie.HotMovie;
import com.atsgg.mycatmovie.fragment.movie.NextMovie;
import com.atsgg.mycatmovie.ui.ViewpagerIndicator;
import com.atsgg.mycatmovie.utils.CacheUtils;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MovieFragment extends BaseFragment {
    @BindView(R.id.ll_movie_location)
    LinearLayout llMovieLocation;
    @BindView(R.id.vpi_movie)
    ViewpagerIndicator vpiMovie;
    @BindView(R.id.vp_movie)
    ViewPager vpMovie;
    @BindView(R.id.tv_movie_city)
    TextView tvMovieCity;


    private String result;

    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mCityChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvMovieCity.setText(CacheUtils.getString(getActivity(), Constants.CITY));
            locate();
        }
    };

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        vpMovie.setAdapter(new MoviePageAdapter(getFragmentManager()));
        vpiMovie.setDatas(mTitles);
        vpiMovie.setViewPager(vpMovie);
    }

    @Override
    protected void initFiled() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mBroadcastManager.registerReceiver(mCityChangeReceiver, new IntentFilter(Constants.CITY_CHANGE));
        if (!TextUtils.isEmpty(CacheUtils.getString(getActivity(), Constants.CITY))) {
            tvMovieCity.setText(CacheUtils.getString(getActivity(), Constants.CITY));
        } else {
            tvMovieCity.setText("北京");
        }
        mFragments = new ArrayList<>();
        mFragments.add(new HotMovie());
        mFragments.add(new NextMovie());
        mFragments.add(new FindMovie());
        mTitles = Arrays.asList("热映", "待映", "找片");

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @OnClick(R.id.ll_movie_location)
    public void onClick() {
        getActivity().startActivity(new Intent(getActivity(), CityActivity.class));
    }

    private class MoviePageAdapter extends FragmentPagerAdapter {


        public MoviePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBroadcastManager.unregisterReceiver(mCityChangeReceiver);
    }

    // 百度定位
    private LocationClient mLocationClient = null;
    private BDLocationListener mLocationListener = new MyLocationListener();


    public void locate() {
        mLocationClient = new LocationClient(UIUtils.getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(mLocationListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                result = location.getCity();
                result = result.replaceAll("市", "");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                result = location.getCity();
                result = result.replaceAll("市", "");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                result = location.getCity();
                result = result.replaceAll("市", "");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                result = "";
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                result = "";
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                result = "";
            }


            if (TextUtils.isEmpty(result)) {
                mLocationClient.stop();
                return;
            }

            Log.e("333", result + "---" + tvMovieCity.getText().toString());

            if (!result.equals(tvMovieCity.getText().toString()) && !TextUtils.isEmpty(result)) {
                // 显示dialog
                showDialog();
            }
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("定位到您当前所在城市在" + result + ",是否切换")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mLocationClient.stop();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvMovieCity.setText(result);
                        CacheUtils.putString(getActivity(), Constants.CITY, result);
                        mLocationClient.stop();
                    }
                }).setCancelable(true).show();

    }


}









