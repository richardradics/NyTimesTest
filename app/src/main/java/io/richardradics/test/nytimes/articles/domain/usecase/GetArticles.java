package io.richardradics.test.nytimes.articles.domain.usecase;

import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.core.domain.usecase.UseCase;

public interface GetArticles extends UseCase<Observable<List<Article>>> {
}
