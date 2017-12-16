package io.richardradics.test.nytimes.core.networking;

import android.app.Application;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.richardradics.test.nytimes.core.di.AppComponent;
import io.richardradics.test.nytimes.core.di.AppModule;
import io.richardradics.test.nytimes.core.di.DaggerAppComponent;

@RunWith(MockitoJUnitRunner.class)
public abstract class MockRestApi {

    @Mock
    private Application application;

    private AppComponent testAppComponent;

    @Before
    public void setUp() throws IOException {
        injectDependencies();
    }

    private void injectDependencies(){
        testAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModuleTest())
                .build();
    }

    public AppComponent getTestAppComponent() {
        return testAppComponent;
    }

}
