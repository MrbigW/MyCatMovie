package com.atsgg.mycatmovie.adapter.city;

import android.content.Context;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.CommonAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.bean.CityBean;

import java.util.List;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class AllCityAdapter extends CommonAdapter<CityBean> {

    public AllCityAdapter(Context context, int layoutId, List<CityBean> datas) {
        super(context, layoutId, datas);
    }


    @Override
    public void convert(ViewHolder holder, final CityBean cityBean) {
        holder.setText(R.id.tvCity, cityBean.getCity());
    }
}