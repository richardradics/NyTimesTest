package io.richardradics.test.nytimes.articles.data.repository.datasource.networking;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.di.ArticlesComponentTest;
import io.richardradics.test.nytimes.articles.di.ArticlesModule;
import io.richardradics.test.nytimes.articles.di.DaggerArticlesComponentTest;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;
import io.richardradics.test.nytimes.core.networking.ArticlesServiceMock;
import io.richardradics.test.nytimes.core.networking.MockRestApi;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GetArticlesMockRestApiTest extends MockRestApi {

    private static final String MOCK_API_KEY = "mock-api-key";

    @Inject
    Retrofit retrofit;

    ArticlesServiceMock articlesServiceMock;

    private final NetworkBehavior behavior = NetworkBehavior.create();

    @Before
    @Override
    public void setUp() throws IOException {
        super.setUp();

        ArticlesComponentTest articlesComponentTest = DaggerArticlesComponentTest.builder()
                .appComponent(getTestAppComponent())
                .articlesModule(new ArticlesModule())
                .build();

        articlesComponentTest.inject(this);

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(behavior).build();

        final BehaviorDelegate<ArticlesRestApi> delegate = mockRetrofit.create(ArticlesRestApi.class);

        articlesServiceMock = new ArticlesServiceMock(delegate);
    }

    @Test
    public void check_get_articles_response_success() throws IOException {

        String json = JsonResourceLoader
                .forResource(getResource())
                .getJson();

        ArticlesResponse articlesResponse = JsonObjectConverter.convertFromJson(json, ArticlesResponse.class);

        TestObserver<ArticlesResponse> testObserver = new TestObserver<>();

        articlesServiceMock.getArticles(MOCK_API_KEY)
                .subscribe(testObserver);

        testObserver.assertValue(articlesResponse);
        testObserver.assertComplete();
    }

    @Test
    public void test_failure_response() throws Exception {
        behavior.setDelay(0, MILLISECONDS);
        behavior.setFailurePercent(100);

        TestObserver<ArticlesResponse> testObserver = new TestObserver<>();

        articlesServiceMock.getArticles(MOCK_API_KEY)
                .subscribe(testObserver);

        testObserver.assertNoValues();
        testObserver.assertError(IOException.class);
    }

    private String getResource() {
        return "json.articles/Articles.json";
    }
}
