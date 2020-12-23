package com.unite.push.receiver;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

//根据业务场景自定义回调
public class HuaWeiMessageService extends HmsMessageService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    }

    @Override
    public void onDeletedMessages() {

    }

    @Override
    public void onMessageSent(String s) {

    }

    @Override
    public void onMessageDelivered(String s, Exception e) {

    }

    @Override
    public void onNewToken(String s) {

    }
}
