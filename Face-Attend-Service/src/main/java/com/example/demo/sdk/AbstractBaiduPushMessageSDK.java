package com.example.demo.sdk;

public abstract class AbstractBaiduPushMessageSDK {

    public void pushMessage(BaiduPushMessageAccount pushAccount, String msg) {}
    public void pushMessageToAll(BaiduPushMessageAccount pushAccount, String msg) {}

}