package io.richardradics.test.nytimes.articles.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.richardradics.test.nytimes.articles.data.repository.ArticlesRepositoryImpl;
import io.richardradics.test.nytimes.articles.data.repository.datasource.ArticlesDataSource;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.ArticlesRestApi;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.ArticlesRestApiDataSource;
import io.richardradics.test.nytimes.articles.data.repository.datasource.orm.ArticlesOrmDataSource;
import io.richardradics.test.nytimes.articles.domain.repository.ArticlesRepository;
import io.richardradics.test.nytimes.articles.domain.usecase.GetArticles;
import io.richardradics.test.nytimes.articles.domain.usecase.GetArticlesImpl;
import io.richardradics.test.nytimes.articles.presentation.navigation.Navigation;
import io.richardradics.test.nytimes.articles.presentation.presenter.ArticleListPresenter;
import io.richardradics.test.nytimes.articles.presentation.presenter.ArticleListPresenterImpl;
import retrofit2.Retrofit;

@Module
public class ArticlesModule {

    @Provides
    @Named("ArticlesOrmDataSource")
    ArticlesDataSource providesOrmDataSource() {
        return new ArticlesOrmDataSource();
    }

    @Provides
    @Named("ArticlesRestApiDataSource")
    ArticlesDataSource providesRestDataSource(ArticlesRestApi articlesRestApi) {
        return new ArticlesRestApiDataSource(articlesRestApi);
    }

    @Provides
    ArticlesRestApi providesArticlesRestApi(Retrofit retrofit) {
        return retrofit.create(ArticlesRestApi.class);
    }

    @Provides
    GetArticles providesGetArticles(ArticlesRepository articlesRepository) {
        return new GetArticlesImpl(articlesRepository);
    }

    @Provides
    Navigation navigation(Context context) {
        return new Navigation(context);
    }

    @Provides
    ArticleListPresenter providesArticleListPresenter(GetArticles getArticles, Navigation navigation) {
        return new ArticleListPresenterImpl(getArticles, navigation);
    }

    @Provides
    ArticlesRepository providesArticlesRepository(@Named("ArticlesOrmDataSource") ArticlesDataSource ormDataSource,
                                                  @Named("ArticlesRestApiDataSource") ArticlesDataSource restApiDataSource) {
        return new ArticlesRepositoryImpl(ormDataSource, restApiDataSource);
    }
}
