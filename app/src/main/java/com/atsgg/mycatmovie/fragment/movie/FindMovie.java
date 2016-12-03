package com.atsgg.mycatmovie.fragment.movie;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.movie.FindGridAdapter;
import com.atsgg.mycatmovie.adapter.movie.FindRecyclerAdapter;
import com.atsgg.mycatmovie.bean.movie.find.FindGridViewBean;
import com.atsgg.mycatmovie.bean.movie.find.FindRecyclerBean;
import com.atsgg.mycatmovie.bean.movie.find.FindTagList;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.DownLoaderUtils;
import com.atsgg.mycatmovie.utils.UIUtils;

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

public class FindMovie extends BaseFragment {


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

    private List<FindTagList.DataBean> mTagListBean;
    private List<FindGridViewBean.DataBean> mGridViewBean;
    private List<FindRecyclerBean.DataBean> mRecyclerBeen;

    @Override
    protected String getUrl() {
        return Constants.URL_FIND_TAGLIST;
    }

    @Override
    protected void initData(String content) {
        processData(content);
    }

    private void processData(String content) {

        mTagListBean = JSON.parseObject(content, FindTagList.class).getData();

        // 为top分类填充数据
        setTopCategoryData();
        // 为GridView填充数据
        setGridViewData();
        // 为RecycleView填充数据
        setRecyclerViewData();
    }

    private void setRecyclerViewData() {
        new DownLoaderUtils().getJsonResult(Constants.URL_FIND_RECYCLER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mRecyclerBeen = JSON.parseObject(s, FindRecyclerBean.class).getData();
                        rvMovieFind.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        rvMovieFind.setAdapter(new FindRecyclerAdapter(getActivity(),R.layout.item_find_recycler_layout, mRecyclerBeen));
                    }
                });
    }


    @Override
    protected void initFiled() {
    }

    private void setGridViewData() {
        new DownLoaderUtils().getJsonResult(Constants.URL_FIND_GRIDVIEW)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mGridViewBean = JSON.parseObject(s, FindGridViewBean.class).getData();
                        gvMovieFind.setAdapter(new FindGridAdapter(getActivity(), mGridViewBean));
                    }
                });
    }

    private void setTopCategoryData() {
        for (int i = 0; i < mTagListBean.get(0).getTagList().size() + 2; i++) {
            TextView tv_type = new TextView(getActivity());
            if (i == mTagListBean.get(0).getTagList().size() + 1) {
                tv_type.setText("   ");
            } else if (i == 0) {
                tv_type.setText("分类");
            } else {
                tv_type.setText(mTagListBean.get(0).getTagList().get(i - 1).getTagName());
            }
            tv_type.setTextSize(16);

            if (i != mTagListBean.get(0).getTagList().size() + 1 && i != 0) {
                tv_type.setTextColor(Color.BLACK);
                tv_type.setBackgroundResource(R.drawable.tv_find_bg);
                tv_type.setPadding(UIUtils.dp2px(15), UIUtils.dp2px(5), UIUtils.dp2px(15), UIUtils.dp2px(5));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(UIUtils.dp2px(5), UIUtils.dp2px(3), UIUtils.dp2px(5), UIUtils.dp2px(3));
            scLlType.addView(tv_type, params);
        }

        for (int i = 0; i < mTagListBean.get(1).getTagList().size() + 2; i++) {
            TextView tv_area = new TextView(getActivity());
            if (i == mTagListBean.get(1).getTagList().size() + 1) {
                tv_area.setText("   ");
            } else if (i == 0) {
                tv_area.setText("地区");
            } else {
                tv_area.setText(mTagListBean.get(1).getTagList().get(i - 1).getTagName());
            }
            tv_area.setTextSize(16);

            if (i != mTagListBean.get(1).getTagList().size() + 1 && i != 0) {
                tv_area.setTextColor(Color.BLACK);
                tv_area.setBackgroundResource(R.drawable.tv_find_bg);
                tv_area.setPadding(UIUtils.dp2px(15), UIUtils.dp2px(5), UIUtils.dp2px(15), UIUtils.dp2px(5));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(UIUtils.dp2px(5), UIUtils.dp2px(3), UIUtils.dp2px(5), UIUtils.dp2px(3));
            scLlArea.addView(tv_area, params);
        }

        for (int i = 0; i < mTagListBean.get(2).getTagList().size() + 2; i++) {
            TextView tv_year = new TextView(getActivity());
            if (i == mTagListBean.get(2).getTagList().size() + 1) {
                tv_year.setText("   ");
            } else if (i == 0) {
                tv_year.setText("年代");
            } else {
                tv_year.setText(mTagListBean.get(2).getTagList().get(i - 1).getTagName());
            }
            tv_year.setTextSize(16);

            if (i != mTagListBean.get(2).getTagList().size() + 1 && i != 0) {
                tv_year.setTextColor(Color.BLACK);
                tv_year.setBackgroundResource(R.drawable.tv_find_bg);
                tv_year.setPadding(UIUtils.dp2px(15), UIUtils.dp2px(5), UIUtils.dp2px(15), UIUtils.dp2px(5));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(UIUtils.dp2px(5), UIUtils.dp2px(3), UIUtils.dp2px(5), UIUtils.dp2px(3));
            scLlYear.addView(tv_year, params);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_findmovie;
    }

}




