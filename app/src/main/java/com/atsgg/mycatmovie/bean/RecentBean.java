package com.atsgg.mycatmovie.bean;

import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

import java.util.List;

/**
 * Created by MrbigW on 2016/12/2.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class RecentBean {


    /**
     * data : {"boardtype":6,"content":"《猫眼想看月度榜》\u2014\u2014将昨日国内待映的影片，按照之前30天的想看数增量从高到低排列，取前50名，每天上午10点更新，相关数据来源于\u201c猫眼电影库\u201d。","created":"2016-12-02","id":6,"movies":[{"dir":"张艺谋","globalReleased":false,"id":246267,"img":"http://p0.meituan.net/w.h/movie/e4a3447ebe8c44eea59ab7f68790c7e2179321.jpeg","monthWish":56812,"nm":"长城","pubDesc":"2016-12-16大陆上映","rt":"2016-12-16","star":"马特·达蒙,景甜,佩德罗·帕斯卡","wish":183083},{"dir":"张嘉佳","globalReleased":false,"id":246388,"img":"http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg","monthWish":53004,"nm":"摆渡人","pubDesc":"2016-12-23大陆上映","rt":"2016-12-23","star":"梁朝伟,金城武,陈奕迅","wish":129770},{"dir":"王宝强","globalReleased":false,"id":248935,"img":"http://p0.meituan.net/w.h/movie/ea67816f455239cd93f22e462d14dadc173993.jpg","monthWish":43261,"nm":"大闹天竺","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"王宝强,白客,岳云鹏","wish":127256},{"dir":"徐克","globalReleased":false,"id":248904,"img":"http://p1.meituan.net/w.h/movie/77cf7c5589c769f6b4ea108362f50532400259.jpg","monthWish":40518,"nm":"西游·伏妖篇","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"吴亦凡,林更新,杨一威","wish":89512},{"dir":"张末","globalReleased":false,"id":343413,"img":"http://p1.meituan.net/w.h/movie/f85de4d42c6fe3e958bd204fcb6a76f59235962.jpg","monthWish":28065,"nm":"28岁未成年","pubDesc":"2016-12-09大陆上映","rt":"2016-12-09","star":"倪妮,霍建华,马苏","wish":94603},{"dir":"周隼","globalReleased":false,"id":1187526,"img":"http://p0.meituan.net/w.h/movie/d4def49e4f1b238c939334f8915bd8ac829204.png","monthWish":18794,"nm":"那年夏天你去了哪里","pubDesc":"2016-12-30大陆上映","rt":"2016-12-30","star":"胡歌,宋佳,颜卓灵","wish":32609},{"dir":"乔阿吉姆·罗恩尼,艾斯彭·山德伯格","globalReleased":false,"id":246012,"img":"http://p0.meituan.net/w.h/movie/a70f3e578657a285b23b2c7cc68ea77a289226.jpg","monthWish":14711,"nm":"加勒比海盗5：死无对证","pubDesc":"2017大陆上映","rt":"2017","star":"约翰尼·德普,奥兰多·布鲁姆,杰弗里·拉什","wish":206205},{"dir":"毕志飞","globalReleased":false,"id":340946,"img":"http://p0.meituan.net/w.h/movie/ad48eec319582068c14f3b391d70782c106705.jpeg","monthWish":13839,"nm":"纯洁心灵·逐梦演艺圈","pubDesc":"2017-02-17大陆上映","rt":"2017-02-17","star":"毕志飞,李彦漫,陈思瀚","wish":51943},{"dir":"唐季礼","globalReleased":false,"id":248933,"img":"http://p1.meituan.net/w.h/movie/243a6fb4155161febd1c36d10c9d99f83041992.jpg","monthWish":13710,"nm":"功夫瑜伽","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"成龙,李治廷,张艺兴","wish":31216},{"dir":"罗登","globalReleased":false,"id":1196825,"img":"http://p0.meituan.net/w.h/movie/7afb384ce4e432b816984cb60107b7c297377.jpg","monthWish":13367,"nm":"爱上试睡师","pubDesc":"2016-12-09大陆上映","rt":"2016-12-09","star":"丹尼斯·吴,唐婧,乔杉","wish":13619}],"paging":{"hasMore":true,"limit":10,"offset":0,"total":50},"shareHidden":1,"title":"猫眼想看月度榜"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * boardtype : 6
         * content : 《猫眼想看月度榜》——将昨日国内待映的影片，按照之前30天的想看数增量从高到低排列，取前50名，每天上午10点更新，相关数据来源于“猫眼电影库”。
         * created : 2016-12-02
         * id : 6
         * movies : [{"dir":"张艺谋","globalReleased":false,"id":246267,"img":"http://p0.meituan.net/w.h/movie/e4a3447ebe8c44eea59ab7f68790c7e2179321.jpeg","monthWish":56812,"nm":"长城","pubDesc":"2016-12-16大陆上映","rt":"2016-12-16","star":"马特·达蒙,景甜,佩德罗·帕斯卡","wish":183083},{"dir":"张嘉佳","globalReleased":false,"id":246388,"img":"http://p1.meituan.net/w.h/movie/7f41742a5c2ea24fcd2018ad333451ae193120.jpg","monthWish":53004,"nm":"摆渡人","pubDesc":"2016-12-23大陆上映","rt":"2016-12-23","star":"梁朝伟,金城武,陈奕迅","wish":129770},{"dir":"王宝强","globalReleased":false,"id":248935,"img":"http://p0.meituan.net/w.h/movie/ea67816f455239cd93f22e462d14dadc173993.jpg","monthWish":43261,"nm":"大闹天竺","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"王宝强,白客,岳云鹏","wish":127256},{"dir":"徐克","globalReleased":false,"id":248904,"img":"http://p1.meituan.net/w.h/movie/77cf7c5589c769f6b4ea108362f50532400259.jpg","monthWish":40518,"nm":"西游·伏妖篇","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"吴亦凡,林更新,杨一威","wish":89512},{"dir":"张末","globalReleased":false,"id":343413,"img":"http://p1.meituan.net/w.h/movie/f85de4d42c6fe3e958bd204fcb6a76f59235962.jpg","monthWish":28065,"nm":"28岁未成年","pubDesc":"2016-12-09大陆上映","rt":"2016-12-09","star":"倪妮,霍建华,马苏","wish":94603},{"dir":"周隼","globalReleased":false,"id":1187526,"img":"http://p0.meituan.net/w.h/movie/d4def49e4f1b238c939334f8915bd8ac829204.png","monthWish":18794,"nm":"那年夏天你去了哪里","pubDesc":"2016-12-30大陆上映","rt":"2016-12-30","star":"胡歌,宋佳,颜卓灵","wish":32609},{"dir":"乔阿吉姆·罗恩尼,艾斯彭·山德伯格","globalReleased":false,"id":246012,"img":"http://p0.meituan.net/w.h/movie/a70f3e578657a285b23b2c7cc68ea77a289226.jpg","monthWish":14711,"nm":"加勒比海盗5：死无对证","pubDesc":"2017大陆上映","rt":"2017","star":"约翰尼·德普,奥兰多·布鲁姆,杰弗里·拉什","wish":206205},{"dir":"毕志飞","globalReleased":false,"id":340946,"img":"http://p0.meituan.net/w.h/movie/ad48eec319582068c14f3b391d70782c106705.jpeg","monthWish":13839,"nm":"纯洁心灵·逐梦演艺圈","pubDesc":"2017-02-17大陆上映","rt":"2017-02-17","star":"毕志飞,李彦漫,陈思瀚","wish":51943},{"dir":"唐季礼","globalReleased":false,"id":248933,"img":"http://p1.meituan.net/w.h/movie/243a6fb4155161febd1c36d10c9d99f83041992.jpg","monthWish":13710,"nm":"功夫瑜伽","pubDesc":"2017-01-28大陆上映","rt":"2017-01-28","star":"成龙,李治廷,张艺兴","wish":31216},{"dir":"罗登","globalReleased":false,"id":1196825,"img":"http://p0.meituan.net/w.h/movie/7afb384ce4e432b816984cb60107b7c297377.jpg","monthWish":13367,"nm":"爱上试睡师","pubDesc":"2016-12-09大陆上映","rt":"2016-12-09","star":"丹尼斯·吴,唐婧,乔杉","wish":13619}]
         * paging : {"hasMore":true,"limit":10,"offset":0,"total":50}
         * shareHidden : 1
         * title : 猫眼想看月度榜
         */

        private int boardtype;
        private String content;
        private String created;
        private int id;
        private PagingBean paging;
        private int shareHidden;
        private String title;
        private List<MoviesBean> movies;

        public int getBoardtype() {
            return boardtype;
        }

        public void setBoardtype(int boardtype) {
            this.boardtype = boardtype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public int getShareHidden() {
            return shareHidden;
        }

        public void setShareHidden(int shareHidden) {
            this.shareHidden = shareHidden;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class PagingBean {
            /**
             * hasMore : true
             * limit : 10
             * offset : 0
             * total : 50
             */

            private boolean hasMore;
            private int limit;
            private int offset;
            private int total;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class MoviesBean extends NextMovieHeaderBean implements ISuspensionInterface {
            /**
             * dir : 张艺谋
             * globalReleased : false
             * id : 246267
             * img : http://p0.meituan.net/w.h/movie/e4a3447ebe8c44eea59ab7f68790c7e2179321.jpeg
             * monthWish : 56812
             * nm : 长城
             * pubDesc : 2016-12-16大陆上映
             * rt : 2016-12-16
             * star : 马特·达蒙,景甜,佩德罗·帕斯卡
             * wish : 183083
             */

            private String dir;
            private boolean globalReleased;
            private int id;
            private String img;
            private int monthWish;
            private String nm;
            private String pubDesc;
            private String rt;
            private String star;
            private int wish;

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public boolean isGlobalReleased() {
                return globalReleased;
            }

            public void setGlobalReleased(boolean globalReleased) {
                this.globalReleased = globalReleased;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getMonthWish() {
                return monthWish;
            }

            public void setMonthWish(int monthWish) {
                this.monthWish = monthWish;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public String getPubDesc() {
                return pubDesc;
            }

            public void setPubDesc(String pubDesc) {
                this.pubDesc = pubDesc;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }
        }
    }
}
