package com.atsgg.mycatmovie.activity;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.common.BaseActivity;
import com.atsgg.mycatmovie.fragment.CinemaFragment;
import com.atsgg.mycatmovie.fragment.FoundFragment;
import com.atsgg.mycatmovie.fragment.MeFragment;
import com.atsgg.mycatmovie.fragment.MovieFragment;
import com.atsgg.mycatmovie.utils.ToastUtil;
import com.atsgg.mycatmovie.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_main_movie)
    ImageView ivMainMovie;
    @BindView(R.id.tv_main_movie)
    TextView tvMainMovie;
    @BindView(R.id.ll_main_movie)
    LinearLayout llMainMovie;
    @BindView(R.id.iv_main_cinema)
    ImageView ivMainCinema;
    @BindView(R.id.tv_main_cinema)
    TextView tvMainCinema;
    @BindView(R.id.ll_main_cinema)
    LinearLayout llMainCinema;
    @BindView(R.id.iv_main_found)
    ImageView ivMainFound;
    @BindView(R.id.tv_main_found)
    TextView tvMainFound;
    @BindView(R.id.ll_main_found)
    LinearLayout llMainFound;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.ll_main_me)
    LinearLayout llMainMe;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private MovieFragment mMovieFragment;
    private CinemaFragment mCinemaFragment;
    private FoundFragment mFoundFragment;
    private MeFragment mMeFragment;

    @Override
    protected void initData() {
        // 默认选择主页
        selectFragment(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.ll_main_movie, R.id.ll_main_cinema, R.id.ll_main_found, R.id.ll_main_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_movie:
                selectFragment(0);
                break;
            case R.id.ll_main_cinema:
                selectFragment(1);
                break;
            case R.id.ll_main_found:
                selectFragment(2);
                break;
            case R.id.ll_main_me:
                selectFragment(3);
                break;
        }
    }

    /**
     * 显示相应的Fragment
     *
     * @param i
     */
    private void selectFragment(int i) {
        mFragmentManager = this.getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        // 隐藏未显示的Fragment;
        hideFragment();

        // 重置ImageView和TextView的选中状态
        resetTab();

        if (i == 0) {
            if (mMovieFragment == null) {
                mMovieFragment = new MovieFragment();
                mTransaction.add(R.id.fl_main, mMovieFragment);
            }
            mTransaction.show(mMovieFragment);

            ivMainMovie.setImageResource(R.drawable.ye);
            tvMainMovie.setTextColor(UIUtils.getColor(R.color.home_back_selected));

        } else if (i == 1) {
            if (mCinemaFragment == null) {
                mCinemaFragment = new CinemaFragment();
                mTransaction.add(R.id.fl_main, mCinemaFragment);
            }
            mTransaction.show(mCinemaFragment);

            ivMainCinema.setImageResource(R.drawable.y7);
            tvMainCinema.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        } else if (i == 2) {
            if (mFoundFragment == null) {
                mFoundFragment = new FoundFragment();
                mTransaction.add(R.id.fl_main, mFoundFragment);
            }
            mTransaction.show(mFoundFragment);

            ivMainFound.setImageResource(R.drawable.y9);
            tvMainFound.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        } else if (i == 3) {
            if (mMeFragment == null) {
                mMeFragment = new MeFragment();
                mTransaction.add(R.id.fl_main, mMeFragment);
            }
            mTransaction.show(mMeFragment);

            ivMainMe.setImageResource(R.drawable.yc);
            tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
        }

        // 切记要提交
        mTransaction.commit();

    }

    private void resetTab() {
        ivMainMovie.setImageResource(R.drawable.yd);
        ivMainCinema.setImageResource(R.drawable.y6);
        ivMainFound.setImageResource(R.drawable.y8);
        ivMainMe.setImageResource(R.drawable.yb);

        tvMainMovie.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainCinema.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainFound.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (mMovieFragment != null) {
            mTransaction.hide(mMovieFragment);
        }
        if (mCinemaFragment != null) {
            mTransaction.hide(mCinemaFragment);
        }
        if (mFoundFragment != null) {
            mTransaction.hide(mFoundFragment);
        }
        if (mMeFragment != null) {
            mTransaction.hide(mMeFragment);
        }
    }


    private boolean isFlag = true;
    private Handler mHandler;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mHandler = new Handler();
        if (isFlag && keyCode == KeyEvent.KEYCODE_BACK) {
            isFlag = false;
            ToastUtil.showToast(MainActivity.this, "再点一次推出哦亲~");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isFlag = true;
                }
            }, 2000);
            return true;
        }


        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁的时候需要Handler移除所有消息和回调
        mHandler.removeCallbacksAndMessages(null);
    }
}
