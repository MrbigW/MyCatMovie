package com.atsgg.mycatmovie.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.adapter.found.FindRecyclerViewAdapter;
import com.atsgg.mycatmovie.bean.found.HeadBean;
import com.atsgg.mycatmovie.bean.found.ListBean;
import com.atsgg.mycatmovie.common.BaseFragment;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.DownLoaderUtils;

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

public class FoundFragment extends BaseFragment {
    @BindView(R.id.find_recycleview)
    RecyclerView findRecycleview;

    private HeadBean headBean;
    private FindRecyclerViewAdapter adapter;
    private ListBean listBean;


    @Override
    protected String getUrl() {
        return Constants.FIND_LIST_URL;
    }

    @Override
    protected void initData(String content) {

        listBean = JSON.parseObject(content, ListBean.class);
        getHeadData();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        findRecycleview.setLayoutManager(manager);
        adapter = new FindRecyclerViewAdapter(getActivity());
        findRecycleview.setAdapter(adapter);
        adapter.refreshList(listBean);
    }


    private void getHeadData() {
        new DownLoaderUtils().getJsonResult(Constants.FIND_HEAD_URL)
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
                        headBean = JSON.parseObject(s, HeadBean.class);
                        adapter.refreshHead(headBean.getData());
                    }
                });
    }

    @Override
    protected void initFiled() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_found;
    }


}
