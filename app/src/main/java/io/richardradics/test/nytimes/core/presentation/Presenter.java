package io.richardradics.test.nytimes.core.presentation;

import io.reactivex.disposables.CompositeDisposable;

public abstract class Presenter<T extends LoadingView> {

    private final CompositeDisposable compositeDispose;

    public Presenter(){
        compositeDispose = new CompositeDisposable();
    }

    public abstract void attachTo(T view);

    public void destroy(){
        compositeDispose.dispose();
    }

    public CompositeDisposable getCompositeDispose() {
        return compositeDispose;
    }
}
