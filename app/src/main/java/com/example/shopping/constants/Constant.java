package com.example.shopping.constants;



import com.example.shopping.apps.MyApp;

import java.io.File;

public class Constant {


    public static final String Base_Home_url = "https://cdwan.cn/api/";



    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/ShiXun";

    public static final int CLICK_TIME = 2000;

    public static int uid;

}
