package io.richardradics.test.nytimes.articles.presentation.presenter;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper.ArticleResponseMapper;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.domain.usecase.GetArticles;
import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;
import io.richardradics.test.nytimes.articles.presentation.navigation.Navigation;
import io.richardradics.test.nytimes.articles.presentation.view.ArticleListView;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;
import io.richardradics.test.nytimes.core.util.RxTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticlesListPresenterTest extends RxTest {

    @Mock
    GetArticles getArticles;

    @Mock
    ArticleListView articleListView;

    @Mock
    Navigation navigation;

    private ArticleListPresenter articleListPresenter;

    @Before
    public void setUp() throws IOException {
        when(getArticles.run()).thenReturn(Observable.just(getMockArticles()));

        articleListPresenter = new ArticleListPresenterImpl(getArticles, navigation);
        articleListPresenter.attachTo(articleListView);
    }

    @Test
    public void verify_add_article_called() {
        verify(articleListView, times(3)).addArticle(any(PresentationArticle.class));
    }

    @Test
    public void verify_navigate_called_when_click() {
        articleListPresenter.clickedOnItem("test");
        verify(navigation, times(1)).navigateToArticleDetails(any());
    }

    public List<Article> getMockArticles() throws IOException {
        final ArticlesResponse articlesResponse = JsonObjectConverter.convertFromJson(getJson(), ArticlesResponse.class);
        return ArticleResponseMapper.transform(articlesResponse);
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.articles/Articles.json")
                .getJson();
    }
}
