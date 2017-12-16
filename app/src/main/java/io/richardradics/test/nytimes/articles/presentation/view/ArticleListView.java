package io.richardradics.test.nytimes.articles.presentation.view;

import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;
import io.richardradics.test.nytimes.core.presentation.LoadingView;

public interface ArticleListView extends LoadingView {
    void addArticle(PresentationArticle presentationArticle);
    void goToDetails(String url);
}
