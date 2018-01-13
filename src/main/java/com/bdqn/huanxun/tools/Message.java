package com.bdqn.huanxun.tools;

/**
 */
public class Message {
    private String code;
    private String msg;

    public static Message success() {
        Message message = new Message();
        message.setCode("0000");
        message.setMsg("操作成功");
        return message;
    }

    public static Message error() {
        Message message = new Message();
        message.setCode("0001");
        message.setMsg("操作失败");
        return message;
    }


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

    private Message(){}
    private Message(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Message failed(){
        return new Message("1001", "操作失败");
    }
    public static Message teacherTimeDupeError(){
        return new Message("1002", "操作失败，教师排课时间冲突");
    }
}
