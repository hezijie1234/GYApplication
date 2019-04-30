package com.gongyou.firstcode.rxjava.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gongyou.firstcode.R;
import com.gongyou.firstcode.cachedir.demo.RequestInterface;
import com.gongyou.firstcode.cachedir.demo.Translations;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.view.RxView;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hezijie on 2019/4/23.
 */

public class RxjavaStudyActivity extends AppCompatActivity {

    private static final String TAG = "111";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_study);
    }

    public void baseStudy(View view) {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.fromArray(new Integer[]{1, 2, 3});
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("111", "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.e("111", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("111", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e("111", "onComplete: ");
            }
        };

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e("111", "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("111", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("111", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e("111", "onComplete: ");
            }
        };
        observable.subscribe(observer);
    }

    private Disposable disposable = null;

    public void baseChain(View view) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("111", "onSubscribe: ");
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("111", "onNext: " + integer);
                if (integer == 2) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("111", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e("111", "onComplete: ");
            }
        });
    }

    public void convertOperation(View view) {
//        mapTest();

        //flatMap的使用
//        flatMapTest();

        //concatMap的使用
//        concatMap();

        //buffer的使用
        bufferMap();
    }

    private void bufferMap() {
        Observable.just(1, 2, 3, 4).buffer(3, 2).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                for (int i = 0; i < integers.size(); i++) {
                    Log.e(TAG, "accept: " + integers.get(i));
                }
            }
        });

    }

    private void concatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
//                e.onComplete();
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
//                String[] strArr = new String[3];
                final List<String> strArr = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    strArr.add("事件" + integer + "的子事件" + i);
                }
                return Observable.fromIterable(strArr);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });
    }

    private void flatMapTest() {
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onNext(5);
//                e.onNext(6);
//                e.onNext(7);
//                e.onNext(8);
////                e.onComplete();
//            }
//        }).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
////                String[] strArr = new String[3];
//                final List<String> strArr = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    strArr.add("事件" + integer + "的子事件" + i);
//                }
//                return Observable.fromIterable(strArr);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e(TAG, "accept: " + s  );
//            }
//        });
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    private void mapTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "将事件" + integer + "转换成字符串";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void concatOperation(View view) {
        concatTest();

//        mergeTest();
    }

    private void mergeTest() {
        Observable.mergeArray(Observable.just(1, 2, 3, 4), Observable.just(5, 6, 7, 8)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: " + integer);
            }
        });
    }

    private void concatTest() {
        Observable.concatArray(Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "accept: " + aLong);
            }
        });
    }

    public void filterOperation(View view) {
        //过滤操作符，与
//        filterAndSkip();
        //过滤事件序列中重复的事件 / 连续重复的事件
        distinctTest();
    }

    private void distinctTest() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("he");
                e.onNext("zi");
                e.onNext("jie");
                e.onNext("he");
                e.onNext("zi");
                e.onNext("jie");
                e.onNext("jie");
                e.onNext("jie");

            }
        }).distinctUntilChanged().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });
    }

    private void filterAndSkip() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onNext(7);
                e.onComplete();

            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {

                return integer > 3;
            }
        }).skip(1)
                .skipLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "accept: " + integer);
                    }
                });
    }

    public void convertThread(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://fy.iciba.com/")
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Observable<Translations> observable = requestInterface.getCall();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translations>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translations translations) {
                Log.e(TAG, "onNext: " + translations );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Subscription mSubscription;
    Button btn;
    public void backpressureTest(View view) {
        btn  = (Button) findViewById(R.id.btn);
        // 被观察者：一共需要发送500个事件，但真正开始发送事件的前提 = FlowableEmitter.requested()返回值 ≠ 0
// 观察者：每次接收事件数量 = 48（点击按钮）

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, "观察者可接收事件数量 = " + emitter.requested());
                boolean flag; //设置标记位控制


                // 被观察者一共需要发送500个事件
                for (int i = 0; i < 500; i++) {
                    flag = false;

                    // 若requested() == 0则不发送
                    while (emitter.requested() == 0) {
                        if (!flag) {
                            Log.d(TAG, "不再发送");
                            flag = true;
                        }
                    }
                    // requested() ≠ 0 才发送
                    Log.d(TAG, "发送了事件" + i + "，观察者可接收事件数量 = " + emitter.requested());
                    emitter.onNext(i);


                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()) // 设置被观察者在io线程中进行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中进行
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                        // 初始状态 = 不接收事件；通过点击按钮接收事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });


// 点击按钮才会接收事件 = 48 / 次

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSubscription.request(48);
                // 点击按钮 则 接收48个事件
            }

        });

//        Flowable.create(new FlowableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
//                for (int i = 0; i < 127; i++) {
//                    e.onNext(i);
//                }
//                e.onComplete();
//            }
//        }, BackpressureStrategy.ERROR)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                Log.e(TAG, "onSubscribe: " );
//                s.request(Long.MAX_VALUE);
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.e(TAG, "onNext: " + integer );
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                t.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "onComplete: " );
//            }
//        });
    }

    public void backpressureBase(View view) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
//                boolean flag;

                for (int i = 0; i < 500; i++) {
//                    flag = false;

                    // 若requested() == 0则不发送
                    while (e.requested() == 0) {
//                        if (!flag) {
//                            Log.d(TAG, "不再发送");
//                            flag = true;
//                        }
                    }
                    Log.e(TAG, "subscribe: 发送了事件" + i + "观察者可接收事件数量" + e.requested() );
                    e.onNext(i);

                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    private int count = 0;
    public void pollingDemo(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://fy.iciba.com/")
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Observable<Translations> observable = requestInterface.getCall();
        observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        if (count < 3){
                            return Observable.just(1).delay(2,TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(new Throwable("轮询结束"));
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translations>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translations translations) {
                count++;
                Log.e(TAG, "onNext: " + translations );
            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: " + e.getMessage() );
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void controllClick(View view) {
        Button button = findViewById(R.id.request);
        RxView.clicks(button).throttleFirst(2,TimeUnit.SECONDS).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext: 按钮被点击" );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    private int maxConnectCount = 10;
    private int currentConnectCount;
    private int waitTime;
    public void netErrorRepeat(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://fy.iciba.com/")
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Observable<Translations> observable = requestInterface.getCall();
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof IOException){
                            if (currentConnectCount < maxConnectCount){
                                currentConnectCount++;
                                Log.e(TAG, "apply: 重试次数" + currentConnectCount );
                                waitTime = 1000 + currentConnectCount* 1000;
                                return Observable.just(1).delay(waitTime,TimeUnit.MILLISECONDS);
                            }else {
                                return Observable.error(new Throwable("重连次数已超过" + currentConnectCount));
                            }
                        }else {
                            return Observable.error(new Throwable("发生非网络错误"));
                        }

                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translations>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translations translations) {
                Log.e(TAG, "onNext: " + translations );
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
