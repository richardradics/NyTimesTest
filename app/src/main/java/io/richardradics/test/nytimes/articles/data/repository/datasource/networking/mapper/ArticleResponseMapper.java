package io.richardradics.test.nytimes.articles.data.repository.datasource.networking.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.ArticlesResponse;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.MediaMetadatum;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.Medium;
import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.Result;
import io.richardradics.test.nytimes.articles.domain.model.Article;

public class ArticleResponseMapper {

    public static List<Article> transform(@NonNull ArticlesResponse response) {
        List<Article> articles = new ArrayList<>();

        for (Result result : response.results()) {
            Article.Builder builder = Article.builder();

            builder.setId(result.id());
            builder.setTitle(result.title());
            builder.setDescription(result._abstract());
            builder.setPublishedDate(result.publishedDate());
            builder.setSection(result.section());
            builder.setType(result.type());
            builder.setUrl(result.url());
            builder.setSource(result.source());

            if (result.media() != null && !result.media().isEmpty()) {
                for (Medium medium : result.media()) {
                    if ("image".equals(medium.type())) {
                        for (MediaMetadatum metadatum : medium.mediaMetadata()) {
                            if (metadatum.height() > 300) {
                                builder.setImageUrl(metadatum.url());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            articles.add(builder.build());
        }
        return articles;
    }

}
