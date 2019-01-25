package com.example.miwei.rxapplication.widget.rxserchview.inner;


import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class BaseValueObservable<T> extends Observable<T> {

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        subscribeListener(observer);
        observer.onNext(getInitialValue());
    }

    protected abstract void subscribeListener(Observer<? super T> observer);
    protected abstract T getInitialValue();

    public final Observable<T> skipInitialValue() {
        return new Skipped();
    }

    private final class Skipped extends Observable<T> {
        Skipped() {
        }

        @Override
        protected void subscribeActual(Observer<? super T> observer) {
            subscribeListener(observer);
        }
    }
}
