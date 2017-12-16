package io.richardradics.test.nytimes.core.networking;

import java.io.IOException;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.ArticlesRestApi;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;
import retrofit2.mock.BehaviorDelegate;

public class ArticlesServiceMock {

    private final BehaviorDelegate<ArticlesRestApi> delegate;

    public ArticlesServiceMock(BehaviorDelegate<ArticlesRestApi> delegate) {
        this.delegate = delegate;
    }

    public Observable<ArticlesResponse> getArticles(String apiKey) {
        ArticlesResponse articlesResponse = null;
        try {
            String json = JsonResourceLoader
                    .forResource("json.articles/Articles.json")
                    .getJson();
            articlesResponse = JsonObjectConverter.convertFromJson(json, ArticlesResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return delegate.returningResponse(articlesResponse).getArticles("apikey");
    }

}
