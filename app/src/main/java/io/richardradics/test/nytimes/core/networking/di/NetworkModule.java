package io.richardradics.test.nytimes.core.networking.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.richardradics.test.nytimes.BuildConfig;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.Medium;
import io.richardradics.test.nytimes.core.networking.gson.AutoValueGsonAdapterFactory;
import io.richardradics.test.nytimes.core.networking.gson.MediumListTypeAdapter;
import io.richardradics.test.nytimes.core.networking.gson.NullOnEmptyConverterFactory;
import io.richardradics.test.nytimes.core.networking.gson.StringListTypeAdapter;
import io.richardradics.test.nytimes.core.networking.http.HttpHeadersInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final int CONNECTION_TIME_OUT = 120000;
    private static final int READ_TIME_OUT = 120000;
    private static final String API_URL = "http://api.nytimes.com/svc/mostpopular/v2/";

    @Provides
    @Named("baseUrl")
    String providesBaseUrl() {
        return API_URL;
    }

    @Provides
    Gson providesGson() {
        Type stringListType = new TypeToken<List<String>>() {}.getType();
        Type mediumListType = new TypeToken<List<Medium>>() {}.getType(); //TODO refactor api specific code

        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonAdapterFactory.create())
                .registerTypeAdapter(stringListType, new StringListTypeAdapter())
                .registerTypeAdapter(mediumListType, new MediumListTypeAdapter()) //TODO refactor api specific code
                .serializeNulls()
                .create();
    }

    @Provides
    GsonConverterFactory providesGsonConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    List<Interceptor> providesOkHttpInterceptors(
            @Named("httpHeadersInterceptor") Interceptor httpHeadersInterceptor,
            @Named("loggingInterceptor") Interceptor loggingInterceptor) {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(httpHeadersInterceptor);
        if (BuildConfig.DEBUG) {
            interceptors.add(loggingInterceptor);
        }
        return interceptors;
    }

    @Provides
    @Named("httpHeadersInterceptor")
    Interceptor providesHttpHeadersInterceptor() {
        return new HttpHeadersInterceptor();
    }

    @Provides
    @Named("loggingInterceptor")
    Interceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    OkHttpClient providesOkHttpClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        for (Interceptor interceptor : interceptors) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        return httpClientBuilder
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    protected Retrofit providesRetrofit(GsonConverterFactory converterFactory,
                              OkHttpClient okHttpClient,
                              @Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

}