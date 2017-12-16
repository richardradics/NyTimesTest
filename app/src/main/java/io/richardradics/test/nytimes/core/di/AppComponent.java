package io.richardradics.test.nytimes.core.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.richardradics.test.nytimes.core.networking.di.NetworkModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {

    Retrofit retrofit();
    Application application();
    Context context();
}
