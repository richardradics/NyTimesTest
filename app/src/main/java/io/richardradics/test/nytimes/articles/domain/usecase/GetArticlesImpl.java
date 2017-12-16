package io.richardradics.test.nytimes.articles.domain.usecase;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.domain.repository.ArticlesRepository;

public class GetArticlesImpl implements GetArticles {

    private final ArticlesRepository articlesRepository;

    public GetArticlesImpl(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public Observable<List<Article>> run() {
        return articlesRepository.getArticles();
    }
}
