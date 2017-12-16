package io.richardradics.test.nytimes.articles.presentation.mapper;

import io.reactivex.functions.Function;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;

public class PresentationArticleMapper {

    public static Function<Article, PresentationArticle> transformForm() {
        return PresentationArticleMapper::transformFrom;
    }

    public static PresentationArticle transformFrom(Article article) {
        PresentationArticle.Builder builder = PresentationArticle.builder();

        builder.title(article.title());
        builder.description(article.description());
        builder.imageUrl(article.imageUrl());
        builder.publishedDate(article.publishedDate());
        builder.url(article.url());

        return builder.build();
    }
}
