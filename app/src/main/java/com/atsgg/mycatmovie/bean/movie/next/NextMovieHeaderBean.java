package com.atsgg.mycatmovie.bean.movie.next;

import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/1.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */
public class NextMovieHeaderBean  implements ISuspensionInterface {

    private List<MovieNextBean.DataBean.ComingBean> mComingBeen;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public NextMovieHeaderBean() {
    }

    public NextMovieHeaderBean(List<MovieNextBean.DataBean.ComingBean> data, String suspensionTag) {
        this.mComingBeen = data;
        this.suspensionTag = suspensionTag;
    }

    public List<MovieNextBean.DataBean.ComingBean> getComingBeen() {
        return mComingBeen;
    }

    public NextMovieHeaderBean setComingBeen(List<MovieNextBean.DataBean.ComingBean> data) {
        this.mComingBeen = data;
        return this;
    }

    public NextMovieHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }


    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }
}
