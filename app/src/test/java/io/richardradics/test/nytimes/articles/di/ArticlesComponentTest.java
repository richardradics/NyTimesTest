package io.richardradics.test.nytimes.articles.di;

import dagger.Component;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.ArticlesRestApi;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.GetArticlesMockRestApiTest;
import io.richardradics.test.nytimes.core.di.AppComponent;
import io.richardradics.test.nytimes.core.networking.MockRestApi;

@ArticlesScopeTest
@Component(dependencies = AppComponent.class, modules = { ArticlesModule.class })
public interface ArticlesComponentTest {
    void inject(GetArticlesMockRestApiTest articlesRestApiMockRestApi);
}
