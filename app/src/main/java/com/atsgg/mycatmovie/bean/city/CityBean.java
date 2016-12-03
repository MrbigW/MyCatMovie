package com.atsgg.mycatmovie.bean.city;


import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityBean extends BaseIndexPinyinBean {

    public CityBean(String name) {
        this.city = name;
    }


    public CityBean() {
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "cityid='" + cityid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", citycode='" + citycode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    /**
     * cityid : 1
     * parentid : 0
     * citycode : 101010100
     * city : 北京
     */

    private String cityid;
    private String parentid;
    private String citycode;
    private String city;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getTarget() {
        return city;
    }
}
