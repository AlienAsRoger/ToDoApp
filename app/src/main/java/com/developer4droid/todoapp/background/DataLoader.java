package com.developer4droid.todoapp.background;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 19.08.2017
 * Time: 7:21
 */

public class DataLoader {


	private CompositeDisposable disposables = new CompositeDisposable();

	public void loadData(DataReceiver<Object> dataReceiver) {
		DisposableObserver<Boolean> disposableObserver = getDisposableObserver();

		getObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(disposableObserver);

		disposables.add(disposableObserver);

	}

	private DisposableObserver<Boolean> getDisposableObserver() {
		return new DisposableObserver<Boolean>() {

			@Override
			public void onComplete() {
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onNext(Boolean bool) {
			}
		};
	}

	private Observable<Boolean> getObservable() {
		return Observable.just(true)
				.map(aBoolean -> {
//							_log("Within Observable");
//							_doSomeLongOperation_thatBlocksCurrentThread();
					return aBoolean;
				});
	}

}
