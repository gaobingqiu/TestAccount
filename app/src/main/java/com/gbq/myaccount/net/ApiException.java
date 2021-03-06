package com.gbq.myaccount.net;

public class ApiException extends RuntimeException {
    public static final int CODE_TIMEOUT = 1000;
    public static final int CODE_UNCONNECTED = 1001;
    public static final int CODE_MALFORMEDJSON = 1020;
    public static final int CODE_DEFAULT = 1003;
    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String MALFORMED_JSON_EXCEPTION = "数据解析错误";

    ApiException(int resultCode, String msg) {
        this(getApiExceptionMessage(resultCode, msg));
    }

    private ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code, String msg) {
        String message;
        switch (code) {
            default:
                message = code + "#" + msg;
        }
        return message;
    }
}

