package io.richardradics.test.nytimes.articles.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.richardradics.test.nytimes.articles.data.repository.datasource.ArticlesDataSource;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper.ArticleResponseMapper;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.domain.repository.ArticlesRepository;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticlesRepositoryImplTest {

    @Mock
    private ArticlesDataSource restArticleDataSource;

    @Mock
    private ArticlesDataSource ormArticleDataSource;

    private ArticlesRepository articlesRepository;

    @Before
    public void setUp() throws IOException {
        when(restArticleDataSource.getArticles()).thenReturn(Observable.just(getMockArticles()));
        when(ormArticleDataSource.getArticles()).thenReturn(Observable.empty());

        articlesRepository = new ArticlesRepositoryImpl(ormArticleDataSource, restArticleDataSource);
    }

    @Test
    public void empty_database_rest_must_called() throws IOException {
        TestObserver<List<Article>> testObserver = TestObserver.create();

        articlesRepository.getArticles().subscribe(testObserver);

        testObserver.assertValue(getMockArticles());
        testObserver.assertComplete();
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
