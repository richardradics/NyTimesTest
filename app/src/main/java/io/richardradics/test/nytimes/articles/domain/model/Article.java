package io.richardradics.test.nytimes.articles.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Article {

    @NonNull
    public abstract Long id();
    @NonNull
    public abstract String url();
    @NonNull
    public abstract String section();
    @NonNull
    public abstract String title();
    @NonNull
    public abstract String description();
    @NonNull
    public abstract String type();
    @NonNull
    public abstract String source();
    @NonNull
    public abstract String publishedDate();
    @Nullable
    public abstract String imageUrl();

    public static Article.Builder builder() {
        return new AutoValue_Article.Builder();
    }

    public static TypeAdapter<Article> typeAdapter(Gson gson){
        return new AutoValue_Article.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Article.Builder setId(Long id);
        public abstract Article.Builder setUrl(String url);
        public abstract Article.Builder setSection(String section);
        public abstract Article.Builder setTitle(String title);
        public abstract Article.Builder setDescription(String description);
        public abstract Article.Builder setType(String type);
        public abstract Article.Builder setSource(String source);
        public abstract Article.Builder setPublishedDate(String publishedDate);
        public abstract Article.Builder setImageUrl(String imageUrl);
        public abstract Article build();
    }
}
