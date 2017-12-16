package io.richardradics.test.nytimes.articles.presentation.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PresentationArticle {

    public abstract String title();
    public abstract String description();
    public abstract String url();
    @Nullable
    public abstract String imageUrl();
    public abstract String publishedDate();

    public static PresentationArticle.Builder builder(){
        return new AutoValue_PresentationArticle.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract PresentationArticle.Builder title(String title);
        public abstract PresentationArticle.Builder description(String descripion);
        public abstract PresentationArticle.Builder url(String url);
        public abstract PresentationArticle.Builder imageUrl(String imageUrl);
        public abstract PresentationArticle.Builder publishedDate(String publishedDate);
        public abstract PresentationArticle build();
    }
}
