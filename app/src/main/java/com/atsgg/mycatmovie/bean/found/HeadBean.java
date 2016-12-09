package com.atsgg.mycatmovie.bean.found;

import java.util.List;

/**
 *发现页面--头-GridView显示的数据
 */
public class HeadBean {

    /**
     * description : 
     * id : 39
     * image : {"authorId":0,"height":86,"id":2719364,"sizeType":0,"targetId":39,"targetType":8,"url":"http://p0.meituan.net/movie/5acd468360744ef1358d2e7276e5c5504617.png","width":86}
     * tag : 
     * title : 今日TOP10
     * url : meituanmovie://www.meituan.com/web?url=http://t.meituan.com/Tjf4B5
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String description;
        private String id;
        /**
         * authorId : 0
         * height : 86
         * id : 2719364
         * sizeType : 0
         * targetId : 39
         * targetType : 8
         * url : http://p0.meituan.net/movie/5acd468360744ef1358d2e7276e5c5504617.png
         * width : 86
         */

        private ImageBean image;
        private String tag;
        private String title;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public static class ImageBean {
            private String authorId;
            private String height;
            private String id;
            private String sizeType;
            private String targetId;
            private String targetType;
            private String url;
            private String width;

            public String getAuthorId() {
                return authorId;
            }

            public void setAuthorId(String authorId) {
                this.authorId = authorId;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSizeType() {
                return sizeType;
            }

            public void setSizeType(String sizeType) {
                this.sizeType = sizeType;
            }

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public String getTargetType() {
                return targetType;
            }

            public void setTargetType(String targetType) {
                this.targetType = targetType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }
        }
    }
}
