package com.example.demo.sdk;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;

public class pushMsgToAll extends AbstractBaiduPushMessageSDK {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BaiduPushClient pushClient;
	 
    public pushMsgToAll(String appKey, String secretKey) {
        // 1. get apiKey and secretKey from developer console
       // String apiKey = "xxxxxxxxxxxxxxxxxxxx";
        //String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(appKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
        	@Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
    }
    public void pushMessageToAll(BaiduPushMessageAccount pushAccount, String msg) {
    	logger.info("pushMessage msg:" + msg);
        try {
            // 4. specify request arguments
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)).addMessageType(0)
                    .addMessage(msg)
                    // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送
                    .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    addDeviceType(3);
            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            // Http请求返回值解析
            logger.info("push msgId : " + response.getMsgId() + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                //throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                //throw e;
            } else {
            	logger.info(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        logger.info("pushMessage finished");
    }
}