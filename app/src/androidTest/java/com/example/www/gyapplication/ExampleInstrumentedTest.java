package com.example.www.gyapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.*;
import android.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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

    @Test
    public void  convert(){
        String str = "abcdefg";
        String result = "";
        StringBuffer sb = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        result = sb.toString();

        boolean contains = str.contains("111");
    }

    @Test
    public void aes(){
        String result = null;
        try {
//            result = AESHelper.encrypt("e9c8e878ee8e2658", android.util.Base64.decode("mQ63D5qG+Og29ngHG0YBKQ==", Base64.DEFAULT));
//                result = AESHelper.decryptNew("e9c8e878ee8e2658","mQ63D5qG+Og29ngHG0YBKQ==","d89fb057f6d4f03g");

//            result = new String(AESTest.decrypt(com.example.www.gyapplication.Base64.decode("mQ63D5qG+Og29ngHG0YBKQ=="),"e9c8e878ee8e2658","d89fb057f6d4f03g"),"utf-8");

            result = decrypt("mQ63D5qG+Og29ngHG0YBKQ==","e9c8e878ee8e2658","d89fb057f6d4f03g");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("111", "aes: " + result );
    }



    /**
     * 解密：对加密后的十六进制字符串(hex)进行解密，并返回字符串
     *
     * @param encryptedStr 需要解密的，加密后的十六进制字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedStr,String key,String iv) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);



            byte[] decode = com.example.www.gyapplication.Base64.decode(encryptedStr);

            //  byte[] bytes = hexStr2Bytes(encryptedStr);
            byte[] original = cipher.doFinal(decode);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
