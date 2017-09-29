package com.gbq.myaccount.util;

import android.util.Log;

import com.gbq.myaccount.constans.Configs;

/**
 * log工具类
 * Created by gbq on 2017-3-20.
 */
public class LogUtil {
	private static final boolean DEBUG = true;
	private static String className;//类名
	private static String methodName;//方法名
	private static int lineNumber;//行数

	private LogUtil(){
	}

	private static boolean isDebuggable() {
		return DEBUG;
	}

	@SuppressWarnings({"StringBufferReplaceableByString", "StringBufferMayBeStringBuilder"})
	private static String createLog(String log ) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(methodName);
		buffer.append("(").append(className).append(":").append(lineNumber).append(")");
		buffer.append(log);
		return buffer.toString();
	}

	private static void getMethodNames(StackTraceElement[] sElements){
		className = sElements[1].getFileName();
		methodName = sElements[1].getMethodName();
		lineNumber = sElements[1].getLineNumber();
	}


	public static void e(String message){
		if (!isDebuggable())
			return;

		// Throwable instance must be created before any methods
		getMethodNames(new Throwable().getStackTrace());
		Log.e(Configs.TAG, createLog(message));
	}


	public static void i(String message){
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.i(Configs.TAG, createLog(message));
	}

	public static void d(String message){
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.d(Configs.TAG, createLog(message));
	}

	public static void v(String message){
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.v(Configs.TAG, createLog(message));
	}

	public static void w(String message){
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.w(Configs.TAG, createLog(message));
	}

	public static void wtf(String message){
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.wtf(Configs.TAG, createLog(message));
	}

}