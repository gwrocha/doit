package com.gw.doit.config;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DoitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("doit.realm").build();
        Realm.setDefaultConfiguration(config);
    }


}
