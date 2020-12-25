package com.unite.push.receiver;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

//根据业务场景自定义回调
public class HuaWeiMessageService extends HmsMessageService {

    @Override
    //接收透传消息方法。
    public void onMessageReceived(RemoteMessage remoteMessage) {

    }

    @Override
    public void onDeletedMessages() {

    }

    @Override
    //发送上行消息成功回调方法。
    public void onMessageSent(String msgId) {

    }

    @Override
    //发送上行消息时如果使用了消息回执能力，消息到达App服务器后，App服务器的应答消息通过本方法回调给应用。
    public void onMessageDelivered(String msgId, Exception exception) {

    }

    @Override
    //服务端更新token回调方法。
    public void onNewToken(String token) {

    }
}
