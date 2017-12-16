package io.richardradics.test.nytimes.articles.di;

import dagger.Component;
import io.richardradics.test.nytimes.articles.di.scope.ArticlesComponentScope;
import io.richardradics.test.nytimes.articles.presentation.ArticleListActivity;
import io.richardradics.test.nytimes.core.di.AppComponent;

@ArticlesComponentScope
@Component(dependencies = AppComponent.class, modules = { ArticlesModule.class })
public interface ArticlesComponent {
    void inject(ArticleListActivity articleListActivity);
}
