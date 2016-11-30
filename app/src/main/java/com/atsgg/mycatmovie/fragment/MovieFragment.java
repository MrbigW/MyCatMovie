package com.atsgg.mycatmovie.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.activity.CityActivity;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.fragment.movie.FindMovie;
import com.atsgg.mycatmovie.fragment.movie.HotMovie;
import com.atsgg.mycatmovie.fragment.movie.NextMovie;
import com.atsgg.mycatmovie.ui.ViewpagerIndicator;

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

    private List<BaseFragment> mFragments;
    private List<String> mTitles;

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
        mFragments = new ArrayList<>();
        mFragments.add(new HotMovie());
        mFragments.add(new NextMovie());
        mFragments.add(new FindMovie());
        mTitles = Arrays.asList("热映", "待映", "照片");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @OnClick(R.id.ll_movie_location)
    public void onClick() {
        getActivity().startActivityForResult(new Intent(getActivity(), CityActivity.class), 0);
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
}









