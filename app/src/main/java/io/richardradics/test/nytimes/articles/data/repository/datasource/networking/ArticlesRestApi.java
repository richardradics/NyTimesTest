package io.richardradics.test.nytimes.articles.data.repository.datasource.networking;


import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesRestApi {

    @GET("mostviewed/all-sections/7.json")
    Observable<ArticlesResponse> getArticles(@Query("api-key") String apiKey);
}
