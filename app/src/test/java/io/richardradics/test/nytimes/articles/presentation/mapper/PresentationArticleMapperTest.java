package io.richardradics.test.nytimes.articles.presentation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.richardradics.test.nytimes.articles.domain.model.Article;
import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PresentationArticleMapperTest {

    private static final String MOCK_TITLE = "Test Title";
    private static final String MOCK_URL = "http://test-url/";
    private static final String MOCK_IMAGE_URL = "http://test-image-url";
    private static final String MOCK_DESCRIPTION = "Test Description";
    private static final String MOCK_PUBLISHED_DATE = "2017-12-16";

    @Test
    public void correctly_transfrom_articles_to_presentation_articles() {
        Article article = getMockArticle();
        PresentationArticle presentationArticle = PresentationArticleMapper.transformFrom(article);
        assertThat(presentationArticle.description(), is(article.description()));
        assertThat(presentationArticle.imageUrl(), is(article.imageUrl()));
        assertThat(presentationArticle.title(), is(article.title()));
        assertThat(presentationArticle.url(), is(article.url()));
        assertThat(presentationArticle.publishedDate(), is(article.publishedDate()));
    }

    private Article getMockArticle() {
        return Article.builder()
                .setTitle(MOCK_TITLE)
                .setUrl(MOCK_URL)
                .setImageUrl(MOCK_IMAGE_URL)
                .setDescription(MOCK_DESCRIPTION)
                .setPublishedDate(MOCK_PUBLISHED_DATE)
                .setId(1L)
                .setSection("Section")
                .setSource("NYTIMES")
                .setType("Type")
                .build();
    }
}
