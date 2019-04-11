package com.gongyou.firstcode.eventbus.demo;

/**
 * Created by hezijie on 2019/4/10.
 */

public class MessageEvent<T> {
    public static final class EventCode {
        public static final int A = 1;
        public static final int B = 2;
        public static final int C = 3;
        public static final int D = 4;
        // other more
    }
    private int code;
    private T data;

    public MessageEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public MessageEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
