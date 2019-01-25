package com.example.miwei.rxapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miwei.rxapplication.R;
import com.example.miwei.rxapplication.widget.rxserchview.RxThinkView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView ResultTextView;

    private DisposableObserver<String> mDisposableObserver;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEditText = findViewById(R.id.edit_search);
        ResultTextView = findViewById(R.id.tv_result);
        initObserver();
        RxThinkView.searchTextChange(searchEditText, new Function<CharSequence, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                return getSearchObservable(charSequence.toString());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(mDisposableObserver);

        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(mDisposableObserver);
    }

    /**
     * 初始化Observer
     */
    private void initObserver() {
        mDisposableObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                ResultTextView.setText(s);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.i("mwww","error");
                ResultTextView.setText("");
            }

            @Override
            public void onComplete() {

            }
        };
    }

    /**
     * 模拟通过输入内容想服务器请求结果
     * @param query
     * @return
     */
    private Observable<String> getSearchObservable(final String query) {
        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                Log.d("SearchActivity", "开始请求，关键词为：" + query);
                try {
                    Thread.sleep(100 + (long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
                Log.d("SearchActivity", "结束请求，关键词为：" + query);
                observableEmitter.onNext("完成搜索，关键词为：" + query);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
