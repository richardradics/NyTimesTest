package io.richardradics.test.nytimes.articles.data.repository.datasource.networking;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.BuildConfig;
import io.richardradics.test.nytimes.articles.data.repository.datasource.ArticlesDataSource;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper.ArticleResponseMapper;
import io.richardradics.test.nytimes.articles.domain.model.Article;

public class ArticlesRestApiDataSource implements ArticlesDataSource {

    private static final String API_KEY = BuildConfig.APY_KEY;

    private final ArticlesRestApi articlesRestApi;


    public ArticlesRestApiDataSource(ArticlesRestApi articlesRestApi) {
        this.articlesRestApi = articlesRestApi;
    }

    public Observable<List<Article>> getArticles() {
        return articlesRestApi.getArticles(API_KEY)
                .map(ArticleResponseMapper::transform);
    }
}
