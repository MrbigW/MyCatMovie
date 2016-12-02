package com.atsgg.mycatmovie.fragment.movie;

import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.common.BaseFragment;

import butterknife.BindView;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class FindMovie extends BaseFragment {

    private String[] types = {"类型", "爱情", "喜剧", "动画", "剧情", "恐怖", "惊悚", "科幻", "动作", "悬疑", "犯罪"};
    private String[] areas = {"地区", "大陆", "美国", "韩国", "日本", "中国香港", "中国台湾", "泰国", "印度", "法国", "英国"};
    private String[] years = {"2017以后", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008"};


    @BindView(R.id.sc_ll_type)
    LinearLayout scLlType;
    @BindView(R.id.sc_ll_area)
    LinearLayout scLlArea;
    @BindView(R.id.sc_ll_year)
    LinearLayout scLlYear;
    @BindView(R.id.gv_movie_find)
    GridView gvMovieFind;
    @BindView(R.id.rv_movie_find)
    RecyclerView rvMovieFind;
    @BindView(R.id.ll_all_awards)
    LinearLayout llAllAwards;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {


    }

    @Override
    protected void initFiled() {



    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_findmovie;
    }

}




