package com.aiseminar.app;

import android.app.Application;

import com.aiseminar.util.EasyPr;

/**
 * Created by lcc_luffy on 2016/6/30.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EasyPr.init(this);
    }
}
