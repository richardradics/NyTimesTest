package io.richardradics.test.nytimes.articles.presentation.presenter;

import io.richardradics.test.nytimes.articles.presentation.view.ArticleListView;
import io.richardradics.test.nytimes.core.presentation.Presenter;

public abstract class ArticleListPresenter extends Presenter<ArticleListView> {
    public abstract void clickedOnItem(String url);
}
