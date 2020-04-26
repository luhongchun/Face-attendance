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
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

public class BaiduPushMessageSDKV3 extends AbstractBaiduPushMessageSDK {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private BaiduPushClient pushClient;

    public BaiduPushMessageSDKV3(String appKey, String secretKey) {
        /*1. 创建PushKeyPair
         *用于app的合法身份认证
         *apikey和secretKey可在应用详情中获取
         
        String apiKey = "73wios1ly9msoxD2EGgyiSEG";
        String secretKey = "pAa1Ui0Z6PI0v5DkdUnRs8zn39PZSMmM";*/
        PushKeyPair pair = new PushKeyPair(appKey, secretKey);
        // 2. 创建BaiduPushClient，访问SDK接口
        pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
        // 3. 注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler (new YunLogHandler () {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
    }

    public void pushMessage(BaiduPushMessageAccount pushAccount, String msg) {
        logger.info("pushMessage msg:" + msg);
        if (pushAccount != null && pushAccount.getBaiduChannelId() != null
                && pushAccount.getBaiduUserId() != null) {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest();
            request.addDeviceType(3);////设置设备类型，deviceType => 1 for web, 2 for pc, 3 for android, 4 for ios, 5 for wp.

            request.addChannelId(pushAccount.getBaiduChannelId())
            .addMsgExpires(new Integer(3600))   //设置消息的有效时间,单位秒,默认3600*5.
            .addMessageType(1)              //设置消息类型,0表示透传消息,1表示通知,默认为0.
            .addMessage(msg);//"{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}"

            try {
            // 5. 执行Http请求
                PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
                /*PushMsgToAllRequest request = new PushMsgToAllRequest()
                        .addMsgExpires(new Integer(3600)).addMessageType(0)
                        .add("Hello Baidu Push.")
                        // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送
                        .addSendTime(System.currentTimeMillis() / 1000 + 70).
                        addDeviceType(3);
                pushClient.pushMsgToAll(request);*/
            // 6. Http请求返回值解析
                logger.info("push msgId : " + response.getMsgId() + ",sendTime: " + response.getSendTime());
            } catch (PushClientException e) {
                //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
                //'true' 表示抛出, 'false' 表示捕获。
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
            } finally {
                request = null;
                msg = null;
            }
        }
        logger.info("pushMessage finished");
    }

}