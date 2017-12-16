package io.richardradics.test.nytimes.articles.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.domain.model.Article;

public interface ArticlesDataSource {

    Observable<List<Article>> getArticles();

}
