package com.tutkal.yazilimsinama;

import android.content.Context;

import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.tutkal.yazilimsinama", appContext.getPackageName());
    }

    public void Login() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        new Alagan(appContext);
        Alagan.Instance.dbString.put("command","userLogin")
                .put("username","teacher")
                .put("password","124")
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        assertEquals("-1","-1");
                    }
                });
    }
}
