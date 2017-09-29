package com.gbq.myaccount.net.api;

@SuppressWarnings("ALL")
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDatas() {
        return data;
    }

    public void setDatas(T datas) {
        this.data = datas;
    }

    public boolean isSuccess(){
        return code == 0 || code == 200;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("error_code=").append(code).append(" msg=").append(msg);
        if (null != data) {
            sb.append(" result:").append(data.toString());
        }
        return sb.toString();
    }
}