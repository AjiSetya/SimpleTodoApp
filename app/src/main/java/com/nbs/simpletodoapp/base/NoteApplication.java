package com.nbs.simpletodoapp.base;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Sidiq on 26/02/2016.
 */
public class NoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
