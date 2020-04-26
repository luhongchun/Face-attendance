package com.attend.face.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.sdk.BaiduPushMessageAccount;
import com.example.demo.sdk.BaiduPushMessageSDKV3;
import com.example.demo.sdk.pushMsgToAll;


@Controller
@RequestMapping(value = "/push")
public class PushController {
	
     /*
     * **单播推送
     */
    @RequestMapping(value = "unicast", method = RequestMethod.GET)
    public String unicast(String studentNo) {
    	String stu = "201413046";
    	BaiduPushMessageSDKV3 sdk = new BaiduPushMessageSDKV3(
    			"9tQ2bQ1YARxbZcasy0oHtWMN", "iL43KkQD9ogvDSOs2uLXANnWWSwA4cwr");
    	BaiduPushMessageAccount a = new BaiduPushMessageAccount();
    	a.setBaiduChannelId("4158027907205098355");
    	a.setBaiduUserId("971334030131653562");
        sdk.pushMessage(a, "{\"title\":\"单播消息推送!\",\"description\":\"Hello Baidu push!\"}");
    	//sdk.pushMessage(a, "{\"title\":\"单播消息推送!\",\"description\":\"Hello Baidu push!\",\"customContentString"+stu+"\"}");
        
        return "hello";
    }
    /*
     * **广播推送
     */
    @RequestMapping(value = "broadcast", method = RequestMethod.GET)
    public String broadcast() {
    	pushMsgToAll sdk = new pushMsgToAll("9tQ2bQ1YARxbZcasy0oHtWMN", "iL43KkQD9ogvDSOs2uLXANnWWSwA4cwr");
    	BaiduPushMessageAccount a = new BaiduPushMessageAccount();
    	/*a.setBaiduChannelId("3914160465502969999");
    	a.setBaiduUserId("762389449824067823");*/
        sdk.pushMessageToAll(a, "{\"title\":\"广播消息推送!\",\"description\":\"Hello Baidu push!\"}");
    	return "hello";
    }
	
	
	
}
