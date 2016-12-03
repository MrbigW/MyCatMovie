package com.atsgg.mycatmovie.bean.movie.find;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/3.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class FindRecyclerBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * festSessionId : 3510
         * festivalId : 108
         * festivalName : 欧洲电影奖
         * heldDate : 2016-12-10
         * img : http://p1.meituan.net/w.h/movie/5287354626e5dbc991753599fb09ba7e90753.jpg
         * movieId : 337359
         * movieName : 她
         * prizeName : 最佳影片(提名)
         * sessionNum : 29
         */

        private int festSessionId;
        private int festivalId;
        private String festivalName;
        private String heldDate;
        private String img;
        private int movieId;
        private String movieName;
        private String prizeName;
        private int sessionNum;

        public int getFestSessionId() {
            return festSessionId;
        }

        public void setFestSessionId(int festSessionId) {
            this.festSessionId = festSessionId;
        }

        public int getFestivalId() {
            return festivalId;
        }

        public void setFestivalId(int festivalId) {
            this.festivalId = festivalId;
        }

        public String getFestivalName() {
            return festivalName;
        }

        public void setFestivalName(String festivalName) {
            this.festivalName = festivalName;
        }

        public String getHeldDate() {
            return heldDate;
        }

        public void setHeldDate(String heldDate) {
            this.heldDate = heldDate;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }

        public int getSessionNum() {
            return sessionNum;
        }

        public void setSessionNum(int sessionNum) {
            this.sessionNum = sessionNum;
        }
    }
}
