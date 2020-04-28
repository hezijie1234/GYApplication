package com.gongyou.flashlight;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

        assertEquals("com.gongyou.flashlight", appContext.getPackageName());
    }

    @Test
    public void sum(){
        float f = 3.011f;
        BigDecimal b = new BigDecimal(f);
        double d = b.setScale(1, BigDecimal.ROUND_CEILING).doubleValue();
        System.out.print("结果" + d);
        Log.e("111", "sum: " + "结果" + d  );
    }
}