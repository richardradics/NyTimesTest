package io.richardradics.test.nytimes.articles.data.repository.datasource.orm;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.data.repository.datasource.ArticlesDataSource;
import io.richardradics.test.nytimes.articles.domain.model.Article;

//TODO implement ORM source
public class ArticlesOrmDataSource implements ArticlesDataSource {

    @Override
    public Observable<List<Article>> getArticles() {
        return Observable.empty();
    }
}
