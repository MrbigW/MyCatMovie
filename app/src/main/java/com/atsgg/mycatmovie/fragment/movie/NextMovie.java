package com.atsgg.mycatmovie.fragment.movie;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.common.HeaderRecyclerAndFooterWrapperAdapter;
import com.atsgg.mycatmovie.adapter.common.ViewHolder;
import com.atsgg.mycatmovie.adapter.movie.MovieNextAdapter;
import com.atsgg.mycatmovie.adapter.movie.PrevueAdapter1;
import com.atsgg.mycatmovie.adapter.movie.RecentAdapter1;
import com.atsgg.mycatmovie.bean.movie.next.MovieNextBean;
import com.atsgg.mycatmovie.bean.movie.next.NextMovieHeaderBean;
import com.atsgg.mycatmovie.bean.movie.next.PrevueBean;
import com.atsgg.mycatmovie.bean.movie.next.RecentBean;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.DownLoaderUtils;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.atsgg.mycatmovie.utils.decoration.DividerItemDecoration;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

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

public class NextMovie extends BaseFragment {


    @BindView(R.id.rv_movie_next)
    RecyclerView rvMovieNext;
    private List<MovieNextBean.DataBean.ComingBean> mComing;

    private List<PrevueBean.DataBean> mPrevueBeen;

    private List<RecentBean.DataBean.MoviesBean> mRecentBeen;

    private LinearLayoutManager mManager;

    private SuspensionDecoration mDecoration;

    private ArrayList<NextMovieHeaderBean> mSourceDatas;

    private ArrayList<NextMovieHeaderBean> mHeaderDatas;

    private MovieNextAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    @Override
    protected String getUrl() {
        return Constants.URL_MOVIE_NEXT;
    }

    @Override
    protected void initData(String content) {
        processComing(content);

    }

    private void getPrevueBean() {
        new DownLoaderUtils().getJsonResult(Constants.URL_MOVIE_PREVUE)
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
                        processPrevue(s);

                    }
                });
    }

    private void getRecentBean() {
        new DownLoaderUtils().getJsonResult(Constants.URL_MOVIE_RECNET)
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
                        processRecent(s);
                    }
                });
    }


    private void processRecent(String s) {
        mRecentBeen = JSON.parseObject(s, RecentBean.class).getData().getMovies();
        initViews();
        setDatas();
    }

    private void processPrevue(String s) {
        mPrevueBeen = JSON.parseObject(s, PrevueBean.class).getData();
        getRecentBean();
    }


    private void initViews() {
        rvMovieNext.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();

        mHeaderDatas.add(new NextMovieHeaderBean(mComing, "预告片推荐"));
        mHeaderDatas.add(new NextMovieHeaderBean(mComing, "近期最受期待"));
        mSourceDatas.addAll(mHeaderDatas);

        mAdapter = new MovieNextAdapter(getActivity(), R.layout.movie_next_item_select_city, mComing);

        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.prevue_item_header_item:
                        //横向recyclerview
                        RecyclerView recyclerView = holder.getView(R.id.rv_prevue);
                        recyclerView.setAdapter(new PrevueAdapter1(getActivity(), R.layout.prevue_item, mPrevueBeen));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        break;
                    case R.layout.recent_item_header_item:
                        //横向recyclerview
                        RecyclerView recyclerView1 = holder.getView(R.id.rv_recent);
                        recyclerView1.setAdapter(new RecentAdapter1(getActivity(), R.layout.recent_item, mRecentBeen));
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        break;
                    case R.layout.header_search_layout:
                        break;
                    default:
                        break;
                }
            }
        };

        mHeaderAdapter.setHeaderView(0, R.layout.header_search_layout, null);
        mHeaderAdapter.setHeaderView(1, R.layout.prevue_item_header_item, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.recent_item_header_item, mHeaderDatas.get(1));

        rvMovieNext.setAdapter(mHeaderAdapter);
        rvMovieNext.addItemDecoration(mDecoration = new SuspensionDecoration(getActivity(), mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()))
                .setColorTitleFont(UIUtils.getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        rvMovieNext.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

    }

    private void setDatas() {
        // 先排序

        mAdapter.setDatas(mComing);
        mHeaderAdapter.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        mSourceDatas.addAll(mComing);
        mDecoration.setmDatas(mSourceDatas);

    }


    @Override
    protected void initFiled() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nextmovie;
    }


    private void processComing(String content) {
        mComing = JSON.parseObject(content, MovieNextBean.class).getData().getComing();
        getPrevueBean();
    }


}
