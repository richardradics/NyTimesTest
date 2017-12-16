package io.richardradics.test.nytimes.articles.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import io.richardradics.test.nytimes.articles.presentation.ArticleDetailActivity;

public class Navigation {

    final Context context;

    @Inject
    public Navigation(Context context) {
        this.context = context;
    }

    public void navigateToArticleDetails(String url) {
        Intent i = new Intent(context, ArticleDetailActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(ArticleDetailActivity.ARG_URL, url);
        context.startActivity(i);
    }

}
