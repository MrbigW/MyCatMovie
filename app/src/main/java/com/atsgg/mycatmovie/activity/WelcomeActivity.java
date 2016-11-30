package com.atsgg.mycatmovie.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.WelBean;
import com.atsgg.mycatmovie.common.BaseActivity;
import com.atsgg.mycatmovie.utils.Constants;
import com.atsgg.mycatmovie.utils.DownLoaderUtils;
import com.atsgg.mycatmovie.utils.ToastUtil;
import com.atsgg.mycatmovie.utils.UIUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_wel)
    ImageView ivWel;

    private WelBean mWelBean;

    @Override
    protected void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        new DownLoaderUtils().getJsonResult(Constants.URL_WEL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        UIUtils.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            }
                        }, mWelBean.getPosters().get(0).getDuration());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(WelcomeActivity.this, e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            processData(s);
                            Glide.with(WelcomeActivity.this)
                                    .load(mWelBean.getPosters().get(0).getPic())
                                    .placeholder(R.drawable.welcome)
                                    .fitCenter()
                                    .into(ivWel);

                        }
                    }
                });

    }

    private void processData(String s) {
        mWelBean = JSON.parseObject(s, WelBean.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


}
