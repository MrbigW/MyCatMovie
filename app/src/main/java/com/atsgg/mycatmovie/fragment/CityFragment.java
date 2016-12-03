package com.atsgg.mycatmovie.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.city.AllCityAdapter;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.HeaderRecyclerAndFooterWrapperAdapter;
import com.atsgg.mycatmovie.adapter.common.OnItemClickListener;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.city.CityBean;
import com.atsgg.mycatmovie.bean.city.CityHeaderBean;
import com.atsgg.mycatmovie.bean.city.CityTopHeaderBean;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.atsgg.mycatmovie.utils.decoration.DividerItemDecoration;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.atsgg.mycatmovie.common.CatMovieApplication.mContext;

/**
 * Created by MrbigW on 2016/12/1.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class CityFragment extends BaseFragment {

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<CityBean> mCityBeen;

    private AllCityAdapter mAdapter;

    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    private SuspensionDecoration mDecoration;

    private String result;
    private String addr;


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;


    private LinearLayoutManager mManager;

    @Override
    protected String getUrl() {
        return Constants.URL_CITY;
    }

    @Override
    protected void initData(String content) {

        locate();

        processData(content);

        initViews();

        setDatas();

        initListener();
    }


    private void initViews() {
        rv.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new CityHeaderBean(locationCity, "定位城市", "定"));
        List<String> recentCitys = new ArrayList<>();
        mHeaderDatas.add(new CityHeaderBean(recentCitys, "最近访问城市", "近"));
        List<String> hotCitys = new ArrayList<>();
        mHeaderDatas.add(new CityHeaderBean(hotCitys, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);

        mAdapter = new AllCityAdapter(getActivity(), R.layout.item_cityall_layout, mCityBeen);

        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.meituan_item_header:
                        final CityHeaderBean meituanHeaderBean = (CityHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mContext, R.layout.meituan_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tv_ct_Name, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (mOnCitySelectListener != null) {
                                                    mOnCitySelectListener.select(new CityBean(cityName));
                                                }
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        break;
                    case R.layout.meituan_item_header_top:
                        CityTopHeaderBean meituanTopHeaderBean = (CityTopHeaderBean) o;
                        holder.setText(R.id.tvCurrent, meituanTopHeaderBean.getTxt());
                        break;
                    default:
                        break;
                }
            }
        };

        mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, new CityTopHeaderBean("定位中..."));
        mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.meituan_item_header, mHeaderDatas.get(1));
        mHeaderAdapter.setHeaderView(3, R.layout.meituan_item_header, mHeaderDatas.get(2));

        rv.setAdapter(mHeaderAdapter);
        rv.addItemDecoration(mDecoration = new SuspensionDecoration(getActivity(), mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(UIUtils.getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
    }

    private void setDatas() {
        // 先排序
        indexBar.getDataHelper().sortSourceDatas(mCityBeen);

        mAdapter.setDatas(mCityBeen);
        mHeaderAdapter.notifyDataSetChanged();
        mSourceDatas.addAll(mCityBeen);

        indexBar.setmSourceDatas(mSourceDatas).invalidate();

        mDecoration.setmDatas(mSourceDatas);

        CityHeaderBean header1 = mHeaderDatas.get(0);
        header1.getCityList().clear();
        header1.getCityList().add("定位中...");

        CityHeaderBean header2 = mHeaderDatas.get(1);
        List<String> recentCitys = new ArrayList<>();
        recentCitys.add("北京");
        recentCitys.add("广州");
        recentCitys.add("西安");
        header2.setCityList(recentCitys);

        CityHeaderBean header3 = mHeaderDatas.get(2);
        List<String> hotCitys = new ArrayList<>();
        hotCitys.add("上海");
        hotCitys.add("北京");
        hotCitys.add("广州");
        hotCitys.add("深圳");
        hotCitys.add("武汉");
        hotCitys.add("天津");
        hotCitys.add("西安");
        hotCitys.add("南京");
        hotCitys.add("杭州");
        hotCitys.add("成都");
        hotCitys.add("重庆");
        header3.setCityList(hotCitys);

        mHeaderAdapter.notifyItemRangeChanged(1, 3);

    }


    @Override
    protected void initFiled() {
        mCityBeen = new ArrayList<>();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_city;
    }

    private void processData(String content) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject cityBean = result.getJSONObject(i);
                CityBean city = new CityBean();
                city.setCity(cityBean.getString("city"));
                mCityBeen.add(city);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


//    /**
//     * 更新数据源
//     *
//     * @param view
//     */
//    public void updateDatas(View view) {
//        for (int i = 0; i < 5; i++) {
//            mCityBeen.add(new CityBean("东京"));
//            mCityBeen.add(new CityBean("大阪"));
//        }
//        //先排序
//        indexBar.getDataHelper().sortSourceDatas(mCityBeen);
//        mSourceDatas.clear();
//        mSourceDatas.addAll(mHeaderDatas);
//        mSourceDatas.addAll(mCityBeen);
//
//        mHeaderAdapter.notifyDataSetChanged();
//        indexBar.invalidate();
//    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (mOnCitySelectListener != null) {
                    mOnCitySelectListener.select((CityBean) o);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private OnCitySelectListener mOnCitySelectListener;

    public void setOnCitySelectListener(OnCitySelectListener onCitySelectListener) {
        mOnCitySelectListener = onCitySelectListener;
    }

    public interface OnCitySelectListener {
        void select(CityBean cityBean);
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
                addr = location.getAddrStr();
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                result = location.getCity();
                result = result.replaceAll("市", "");
                addr = location.getAddrStr();
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                result = location.getCity();
                result = result.replaceAll("市", "");
                addr = location.getAddrStr();
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                result = "定位失败";
                addr = "定位失败";
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                result = "定位失败";
                addr = "定位失败";
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                result = "定位失败";
                addr = "定位失败";
            }


            CityHeaderBean header1 = mHeaderDatas.get(0);
            header1.getCityList().clear();
            header1.getCityList().add(result);


            mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, new CityTopHeaderBean(addr));
            mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, header1);

            mHeaderAdapter.notifyDataSetChanged();
        }
    }


}

















