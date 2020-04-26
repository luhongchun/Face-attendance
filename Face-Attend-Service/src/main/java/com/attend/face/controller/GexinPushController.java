package com.attend.face.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.style.Style0;


@Controller
@RequestMapping(value = "/gexin")
public class GexinPushController {
	private static String appId = "76J19vz1Iy8JPs8OlAAst6";
    private static String appKey = "WFKPM77Z747i9ZOn4t3ol";
    private static String masterSecret = "6ykUzKQdPv8gCbi1RW8vD5";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
     /*
     * **单播推送
     */
	@RequestMapping(value = "unicast", method = RequestMethod.GET)
    public static String unicast(String name,String studentNo) {
		String title =name;
		String Alias = studentNo;
		IGtPush push = new IGtPush(host, appKey, masterSecret);
        LinkTemplate template = linkTemplateDemo(title);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        //target.setClientId(CID);
        target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
        return "hello";
    }
	
	public static LinkTemplate linkTemplateDemo(String title) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("新大研究院考勤");
        style.setText(title+"人脸考勤成功");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl("http://gs.xju.edu.cn");
        return template;
    }
}
