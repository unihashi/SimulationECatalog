package com.simulation.simulationecatalog.cores;

import com.simulation.simulationecatalog.classes.DisposableObservable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class CoreUseCase<T, Parms> {
    protected static final String UNKNOWN_ERROR = "unknown_error";
    private final CompositeDisposable compositeDisposable;
    public CoreUseCase() {
        compositeDisposable = new CompositeDisposable();
    }
    public abstract Observable<T> buildObservable(Parms parms);

    protected DisposableObservable<T> getDisposableObservable() {
        return getDisposableObservable();
    }
    public void execute(DisposableObserver<T> disposable, Parms parms) {
        Observable<T> observable = this.buildObservable(parms)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDispose(observable.subscribeWith(disposable));
    }
    public void execute(Parms parms) {
        Observable<T> observable = this.buildObservable(parms)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDispose(observable.subscribeWith(this.getDisposableObservable()));
    }
    public void clearDispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
    private void addDispose(Disposable disposable) {
        if (disposable != null) {
            compositeDisposable.add(disposable);
        }
    }
    public boolean isDispose() {
        return compositeDisposable.isDisposed();
    }

}