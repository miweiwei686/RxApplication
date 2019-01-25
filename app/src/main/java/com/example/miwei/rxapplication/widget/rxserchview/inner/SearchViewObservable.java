package com.example.miwei.rxapplication.widget.rxserchview.inner;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

public class SearchViewObservable extends BaseValueObservable<CharSequence>{

    private final TextView view;

    public SearchViewObservable(TextView view) {
        this.view = view;
    }


    @Override
    protected void subscribeListener(Observer<? super CharSequence> observer) {
        SearchViewObservable.Listener listener = new SearchViewObservable.Listener(view, observer);
        observer.onSubscribe(listener);
        view.addTextChangedListener(listener);
    }

    @Override
    protected CharSequence getInitialValue() {
        return view.getText();
    }

    final static class Listener extends MainThreadDisposable implements TextWatcher {
        private final TextView view;
        private final Observer<? super CharSequence> observer;

        Listener(TextView view, Observer<? super CharSequence> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!isDisposed()) {
                observer.onNext(s);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        protected void onDispose() {
            Log.i("mwww","searchviewobservable dispose");
            view.removeTextChangedListener(this);
        }
    }
}
