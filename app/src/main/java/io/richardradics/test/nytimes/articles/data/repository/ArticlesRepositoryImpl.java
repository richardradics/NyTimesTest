package io.richardradics.test.nytimes.articles.data.repository;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.data.repository.datasource.ArticlesDataSource;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.domain.repository.ArticlesRepository;

public class ArticlesRepositoryImpl implements ArticlesRepository {

    private final ArticlesDataSource restDataSource;
    private final ArticlesDataSource ormDataSource;

    public ArticlesRepositoryImpl(ArticlesDataSource ormDataSource, ArticlesDataSource restDataSource) {
        this.restDataSource = restDataSource;
        this.ormDataSource = ormDataSource;
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return ormDataSource.getArticles()
                .switchIfEmpty(restDataSource.getArticles());
    }

}
