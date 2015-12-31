package com.hipad.servicetool;

import android.os.Build;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sanndy on 2015/12/29.
 */
public class Tool {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            for (;;) {
                final int result = sNextGeneratedId.get();
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                    newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }
    public static Field[]
    _DumpAllResourceIDs(Class<?> classType )
            throws IllegalArgumentException {
        Field[] fIDs = classType.getFields();

        try {
            for (int i = 0; i < fIDs.length; i++) {
                Field fld = fIDs[i];
                int nID = fld.getInt(null);
                Log.d("dbg",
                        classType.getSimpleName() + " " +
                                i + ": " +
                                fld.getName() + "=" +
                                nID);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return fIDs;
    }
}
