package io.richardradics.test.nytimes.articles.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.richardradics.test.nytimes.R;
import io.richardradics.test.nytimes.articles.di.ArticlesComponent;
import io.richardradics.test.nytimes.articles.di.ArticlesModule;
import io.richardradics.test.nytimes.articles.di.DaggerArticlesComponent;
import io.richardradics.test.nytimes.articles.presentation.adapter.ArticleListAdapter;
import io.richardradics.test.nytimes.articles.presentation.model.PresentationArticle;
import io.richardradics.test.nytimes.articles.presentation.presenter.ArticleListPresenter;
import io.richardradics.test.nytimes.articles.presentation.view.ArticleListView;
import io.richardradics.test.nytimes.core.listener.OnItemClick;
import io.richardradics.test.nytimes.core.presentation.BaseActivity;

public class ArticleListActivity extends BaseActivity implements ArticleListView, OnItemClick<String> {

    @Inject
    ArticleListPresenter articleListPresenter;

    @Inject
    ArticleListAdapter articleListAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView articleList;

    LayoutAnimationController animationController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        injectDependencies();

        animationController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);

        articleListPresenter.attachTo(this);

        articleList.setLayoutManager(new LinearLayoutManager(this));
        articleList.setAdapter(articleListAdapter);
        articleList.setLayoutAnimation(animationController);

        articleListAdapter.setOnItemClickListener(this);
    }

    private void injectDependencies() {
        ButterKnife.bind(this);
        ArticlesComponent articlesComponent = DaggerArticlesComponent.builder()
                .appComponent(getAppComponent())
                .articlesModule(new ArticlesModule())
                .build();
        articlesComponent.inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void addArticle(PresentationArticle presentationArticle) {
        articleListAdapter.addPresentationArticle(presentationArticle);
        articleList.scheduleLayoutAnimation();
    }

    @Override
    public void goToDetails(String url) {
        articleListPresenter.clickedOnItem(url);
    }

    @Override
    public void onClick(String url) {
        goToDetails(url);
    }
}
