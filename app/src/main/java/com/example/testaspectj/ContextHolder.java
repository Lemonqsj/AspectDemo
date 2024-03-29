package com.example.testaspectj;

import android.app.Application;
import android.content.Context;

/**
 * @Auther: Lemon
 * @Date: 2019/7/2 14:28 43
 * @Describe: the infor of the class
 */
public class ContextHolder {


    private final ContextHolder self = this;

    static Context ApplicationContext;

    public static void init(Context context) {
        ApplicationContext = context;
    }


    public static Context getContext() {
        if (ApplicationContext == null) {
            try {
                Application application = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication").invoke(null, (Object[]) null);
                if (application != null) {
                    ApplicationContext = application;
                    return application;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Application application = (Application) Class.forName("android.app.AppGlobals")
                        .getMethod("getInitialApplication").invoke(null, (Object[]) null);
                if (application != null) {
                    ApplicationContext = application;
                    return application;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new IllegalStateException("ContextHolder is not initialed, it is recommend to init with application context.");
        }
        return ApplicationContext;
    }
}
