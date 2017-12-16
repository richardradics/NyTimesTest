package io.richardradics.test.nytimes.articles.presentation.presenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.richardradics.test.nytimes.articles.domain.usecase.GetArticles;
import io.richardradics.test.nytimes.articles.presentation.mapper.PresentationArticleMapper;
import io.richardradics.test.nytimes.articles.presentation.navigation.Navigation;
import io.richardradics.test.nytimes.articles.presentation.view.ArticleListView;

public class ArticleListPresenterImpl extends ArticleListPresenter {

    private final GetArticles getArticles;
    private final Navigation navigation;

    private ArticleListView view;

    public ArticleListPresenterImpl(GetArticles getArticles, Navigation navigation) {
        this.getArticles = getArticles;
        this.navigation = navigation;
    }


    @Override
    public void attachTo(ArticleListView view) {
        this.view = view;
        loadList();
    }

    private void loadList() {
        getCompositeDispose()
                .add(getArticles.run()
                        .subscribeOn(Schedulers.io())
                        .flatMap(Observable::fromIterable)
                        .map(PresentationArticleMapper.transformForm())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(presentationArticle -> view.addArticle(presentationArticle)));

    }

    @Override
    public void clickedOnItem(String url) {
        navigation.navigateToArticleDetails(url);
    }
}
