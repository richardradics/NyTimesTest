package io.richardradics.test.nytimes.articles.domain.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper.ArticleResponseMapper;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.domain.repository.ArticlesRepository;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetArticlesTest {

    @Mock
    private ArticlesRepository articlesRepository;

    @Test
    public void successfull_get_articles() throws IOException {
        when(articlesRepository.getArticles()).thenReturn(Observable.just(getMockArticles()));

        TestObserver<List<Article>> testObserver = TestObserver.create();

        GetArticles getArticles = new GetArticlesImpl(articlesRepository);

        getArticles.run().subscribe(testObserver);

        testObserver.assertResult(getMockArticles());
        testObserver.assertNoErrors();
        testObserver.assertComplete();

        verify(articlesRepository, times(1)).getArticles();
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
