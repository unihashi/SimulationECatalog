package com.simulation.simulationecatalog.classes;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.simulation.simulationecatalog.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class App extends Application {
    public static boolean ENABLE_COACH = true;
    public static App instance;

    public static App instance() {
        return instance;
    }

    private final String DEBUG_TAG = "Virgo-APP";

    @Override
    public void onCreate() {
        super.onCreate();
        //for create global context in singleton
        instance = this;

        //initialize default font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Kanit-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        String versionName = "2.3.0"; //default

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(DEBUG_TAG, e.getMessage());
        }
    }

    public static ObscuredSharedPreferences getSharedPrefernces() {
        return ObscuredSharedPreferences.getPrefs(
                getSharedPrefernces().context,
                Settings.Secure.ANDROID_ID,
                Context.MODE_PRIVATE);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).trimMemory(level);
    }
}