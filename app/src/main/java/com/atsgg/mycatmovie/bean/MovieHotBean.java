package com.atsgg.mycatmovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MrbigW on 2016/11/30.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MovieHotBean implements Serializable {


    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":false,"movies":[{"late":false,"cnms":0,"sn":0,"showInfo":"今天165家影院放映2091场","dir":"大卫·叶茨","star":"埃迪·雷德梅恩,凯瑟琳·沃特斯顿,艾莉森·萨多尔","cat":"冒险,奇幻,家庭","wish":130686,"3d":true,"pn":236,"preSale":0,"vd":"","scm":"神奇动物城，法师显超能","sc":9,"imax":true,"snum":100430,"showDate":"","rt":"2016-11-25上映","dur":133,"src":"","img":"http://p0.meituan.net/165.220/movie/f2820b28cff46c530a1aee47a2c00011274783.jpg","ver":"3D/IMAX 3D/中国巨幕","nm":"神奇动物在哪里","time":"","id":248918},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]}
     */

    private ControlBean control;
    private int status;
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean {
        /**
         * expires : 1800
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {
        /**
         * hasNext : false
         * movies : [{"late":false,"cnms":0,"sn":0,"showInfo":"今天165家影院放映2091场","dir":"大卫·叶茨","star":"埃迪·雷德梅恩,凯瑟琳·沃特斯顿,艾莉森·萨多尔","cat":"冒险,奇幻,家庭","wish":130686,"3d":true,"pn":236,"preSale":0,"vd":"","scm":"神奇动物城，法师显超能","sc":9,"imax":true,"snum":100430,"showDate":"","rt":"2016-11-25上映","dur":133,"src":"","img":"http://p0.meituan.net/165.220/movie/f2820b28cff46c530a1aee47a2c00011274783.jpg","ver":"3D/IMAX 3D/中国巨幕","nm":"神奇动物在哪里","time":"","id":248918},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
         */

        private boolean hasNext;
        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {

            public boolean is3d() {
                return is3d;
            }

            public void setIs3d(boolean is3d) {
                this.is3d = is3d;
            }

            /**
             * late : false
             * cnms : 0
             * sn : 0
             * showInfo : 今天165家影院放映2091场
             * dir : 大卫·叶茨
             * star : 埃迪·雷德梅恩,凯瑟琳·沃特斯顿,艾莉森·萨多尔
             * cat : 冒险,奇幻,家庭
             * wish : 130686
             * 3d : true
             * pn : 236
             * preSale : 0
             * vd :
             * scm : 神奇动物城，法师显超能
             * sc : 9
             * imax : true
             * snum : 100430
             * showDate :
             * rt : 2016-11-25上映
             * dur : 133
             * src :
             * img : http://p0.meituan.net/165.220/movie/f2820b28cff46c530a1aee47a2c00011274783.jpg
             * ver : 3D/IMAX 3D/中国巨幕
             * nm : 神奇动物在哪里
             * time :
             * id : 248918
             */
            private boolean is3d;
            private boolean late;
            private int cnms;
            private int sn;
            private String showInfo;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            private int pn;
            private int preSale;
            private String vd;
            private String scm;
            private float sc;
            private boolean imax;
            private int snum;
            private String showDate;
            private String rt;
            private int dur;
            private String src;
            private String img;
            private String ver;
            private String nm;
            private String time;
            private int id;

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }


            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public float getSc() {
                return sc;
            }

            public void setSc(float sc) {
                this.sc = sc;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
