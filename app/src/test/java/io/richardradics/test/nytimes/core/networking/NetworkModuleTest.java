package io.richardradics.test.nytimes.core.networking;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import io.richardradics.test.nytimes.core.networking.di.NetworkModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class NetworkModuleTest extends NetworkModule {

    List<Interceptor> providesOkHttpInterceptors(@Named("httpHeadersInterceptor") Interceptor httpHeadersInterceptor,
                                                 @Named("loggingInterceptor") Interceptor loggingInterceptor) {
        List<Interceptor> interceptors = new ArrayList<>();
        return interceptors;
    }

}
