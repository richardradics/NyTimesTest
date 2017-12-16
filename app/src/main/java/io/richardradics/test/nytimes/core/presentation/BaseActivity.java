package io.richardradics.test.nytimes.core.presentation;

import android.support.v7.app.AppCompatActivity;

import io.richardradics.test.nytimes.core.application.NyTimesApplication;
import io.richardradics.test.nytimes.core.di.AppComponent;

public class BaseActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return ((NyTimesApplication) getApplication()).getAppComponent();
    }
}
