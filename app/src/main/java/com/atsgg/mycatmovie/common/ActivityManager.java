package com.atsgg.mycatmovie.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: activity管理类
 * -------------------=.=------------------------
 */

public class ActivityManager {

    // 使用饿汉式实现单例模型
    private ActivityManager() {
    }

    private static ActivityManager mInstance = new ActivityManager();

    public static ActivityManager getInstance() {
        return mInstance;
    }

    // 提供操作Acitivity的容器:Stack
    private Stack<Activity> mActivityStack = new Stack<>();

    // Activity的添加
    public void add(Activity activity) {
        if (activity != null) {
            mActivityStack.add(activity);
        }
    }

    // 删除指定的Activity
    public void remove(Activity activity) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            if (activity != null && activity.getClass().equals(mActivityStack.get(i).getClass())) {
                // 销毁当前Activity对象
                mActivityStack.get(i).finish();
                // 将指定的activity对象从栈空间移除
                mActivityStack.remove(i);
            }
        }
    }

    // 删除当前的Acitivity
    public void removeCurrent() {
        mActivityStack.lastElement().finish();
        mActivityStack.remove(mActivityStack.lastElement());
    }

    // 删除所有的Activity
    public void removeAll() {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            // 销毁当前Activity对象
            mActivityStack.get(i).finish();
            // 将指定的activity对象从栈空间移除
            mActivityStack.remove(i);
        }
    }

    // 得到Activity数量
    public int getAcitivitySize() {
        return mActivityStack.size();
    }

}



