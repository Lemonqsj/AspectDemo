package com.example.testaspectj.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.testaspectj.ContextHolder;
import com.example.testaspectj.SecondActivity;
import com.example.testaspectj.SharePreferenceUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @Auther: Lemon
 * @Date: 2019/7/2 14:14 45
 * @Describe: the infor of the class
 */

@Aspect
public class LoginAspect {

    public static final String TAG = LoginAspect.class.getSimpleName();

    @Pointcut("execution(@com.example.testaspectj.annotion.LoginFilter * *(..))")
    public void loginAspectMethod() {
    }


    @Before("loginAspectMethod()")
    public Object loginFilterWithAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        //get context


        Context context = ContextHolder.getContext();
        boolean isLogin = SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, context);

        if (isLogin) {
            Log.d(TAG, "已经登录，跳转到下个界面");
        }else {
            Log.d(TAG, "暂未登录，跳转到登录界面");

            Intent intent=new Intent(context, SecondActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


        //

        return null;
    }
}
