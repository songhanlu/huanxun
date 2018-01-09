package com.bdqn.huanxun.tools;

/**
 * Created by hp on 2018/1/9.
 */
public class Message {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Message(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static Message success(){
        return new Message("1000", "操作成功");
    }
    public static Message failed(){
        return new Message("1001", "操作失败");
    }
}
