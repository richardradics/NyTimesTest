package io.richardradics.test.nytimes.articles.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.domain.model.Article;

public interface ArticlesRepository {

    Observable<List<Article>> getArticles();

}
