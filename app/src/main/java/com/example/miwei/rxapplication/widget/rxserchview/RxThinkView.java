package com.example.miwei.rxapplication.widget.rxserchview;

import android.widget.TextView;

import com.example.miwei.rxapplication.widget.rxserchview.inner.SearchViewObservable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author miwei
 * 该控件用于联想搜索
 * 考虑一下三方面问题：
 * 1、输入完延时一段时间再查询，防止连续输入
 * 2、当输入为空时不发请求
 * 3、当一次输入还没返回时再次发送请求取消之前那次的结果
 */
public final class RxThinkView {

    public static Observable<String> searchTextChange(@Nullable TextView view, Function<CharSequence, ObservableSource<String>> query) {

        if(view != null) {
            return new SearchViewObservable(view).debounce(500, TimeUnit.MILLISECONDS).flatMap(new Function<CharSequence, ObservableSource<CharSequence>>() {
                @Override
                public ObservableSource<CharSequence> apply(CharSequence charSequence) throws Exception {
                    return Observable.just(charSequence);
                }
            }).filter(new Predicate<CharSequence>() {
                @Override
                public boolean test(CharSequence charSequence) throws Exception {
                    return charSequence.length() > 0;
                }
            }).switchMap(query);
        }
        return null;
    }

//    public static Observable<String> searchTextChange(@Nullable TextView view, Function<CharSequence, ObservableSource<String>> query) {
//
//        if(view != null) {
//            return new SearchViewObservable(view).debounce(500, TimeUnit.MILLISECONDS).flatMap(new Function<CharSequence, ObservableSource<CharSequence>>() {
//                @Override
//                public ObservableSource<CharSequence> apply(CharSequence charSequence) throws Exception {
//                    if(charSequence.length() > 0) {
//                        return Observable.just(charSequence);
//                    } else {
//                        return Observable.error(new Throwable());
//                    }
//
//                }
//            }).switchMap(query);
//        }
//        return null;
//    }
}