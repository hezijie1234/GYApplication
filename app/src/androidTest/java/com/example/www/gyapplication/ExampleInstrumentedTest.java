package com.example.www.gyapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        String a = "2";
        if (a.length() - a.indexOf(".") > 2){
            a = a.substring(0,a.indexOf(".") + 3);
        }
        Log.e("111", "useAppContext: " + a  );
//        assertEquals("com.example.www.gyapplication", appContext.getPackageName());
    }

    @Test
    public void testWeakReference() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        WeakReference weakReference = new WeakReference(object,referenceQueue);
        Log.e("111", "weakReference: " + weakReference.get() );
        Log.e("111", "referenceQueue: " + referenceQueue.poll() );
        object = null;
        System.gc();
        System.gc();
        Log.e("111", "weakReference: " + weakReference.get() );
        //如果弱引用所引用的对象被垃圾回收，Java 虚拟机就会把这个弱引用加入到与之关联的引用队列中。referenceQueue.poll()拉取队列中的第一个对象
        Log.e("111", "referenceQueue: " + referenceQueue.poll() );

    }
    @Test
    public void testSoftReference()throws InterruptedException{
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        SoftReference weakReference = new SoftReference(object,referenceQueue);
        Log.e("111", "weakReference: " + weakReference.get() );
        Log.e("111", "referenceQueue: " + referenceQueue.poll() );
        object = null;
        System.gc();
        System.gc();
        Log.e("111", "weakReference: " + weakReference.get() );
        //如果弱引用所引用的对象被垃圾回收，Java 虚拟机就会把这个弱引用加入到与之关联的引用队列中。referenceQueue.poll()拉取队列中的第一个对象
        Log.e("111", "referenceQueue: " + referenceQueue.poll() );
    }
}
