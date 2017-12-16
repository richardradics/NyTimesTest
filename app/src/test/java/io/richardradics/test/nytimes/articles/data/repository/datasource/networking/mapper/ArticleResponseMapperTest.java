package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.core.json.JsonObjectConverter;
import io.richardradics.test.nytimes.core.json.JsonResourceLoader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ArticleResponseMapperTest {

    ArticlesResponse articlesResponse;

    @Before
    public void setUp() throws IOException {
        articlesResponse = JsonObjectConverter.convertFromJson(getJson(), ArticlesResponse.class);
    }

    private String getJson() throws IOException {
        return JsonResourceLoader
                .forResource("json.articles/Articles.json")
                .getJson();
    }

    @Test
    public void correctly_transform_articleresponse_to_domain_article_list() {
        List<Article> articles = ArticleResponseMapper.transform(articlesResponse);

        assertThat(articles.size(), is(3));
    }

}
