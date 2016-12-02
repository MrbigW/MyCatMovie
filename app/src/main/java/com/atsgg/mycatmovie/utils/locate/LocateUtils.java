package com.atsgg.mycatmovie.utils.locate;

public class LocateUtils {



    private static LocateUtils instance;

    private LocateUtils() {

    }

    public static synchronized LocateUtils getInstance() {
        if (instance == null) {
            instance = new LocateUtils();
        }
        return instance;
    }





}