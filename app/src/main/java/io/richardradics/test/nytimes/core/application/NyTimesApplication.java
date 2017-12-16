package io.richardradics.test.nytimes.core.application;

import android.app.Application;

import io.richardradics.test.nytimes.core.di.AppComponent;
import io.richardradics.test.nytimes.core.di.AppModule;
import io.richardradics.test.nytimes.core.di.DaggerAppComponent;
import io.richardradics.test.nytimes.core.networking.di.NetworkModule;

public class NyTimesApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependency();
    }

    private void injectDependency() {
        appComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
