package com.aiseminar.util;

import android.app.Application;

import com.aiseminar.EasyPR.PlateRecognizer;

/**
 * Created by lcc_luffy on 2016/6/30.
 */

public class EasyPr {
    private PlateRecognizer plateRecognizer;
    private static Application context;


    public static void init(Application context) {
        EasyPr.context = context;
    }

    private static class ClassHolder {
        private static EasyPr easyPr = new EasyPr();
    }

    private EasyPr() {
        plateRecognizer = new PlateRecognizer(context);
    }

    public static PlateRecognizer get() {
        return ClassHolder.easyPr.plateRecognizer;
    }
}
