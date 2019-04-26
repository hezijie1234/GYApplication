package com.gongyou.firstcode.cachedir.demo;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hezijie on 2019/4/25.
 */

public interface RequestInterface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translations> getCall();
}
