package com.example.test.bean;

public class Message {
    int type;
    String msg;
    String date;

    public final static int SER_TYPE=0;
    public final static int ME_TYPE=1;

    public Message(int type, String msg, String date){
        this.msg=msg;
        this.type=type;
        this.date=date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public String getMsg() {
        return msg;
    }
    public int getType() {
        return type;
    }
}
