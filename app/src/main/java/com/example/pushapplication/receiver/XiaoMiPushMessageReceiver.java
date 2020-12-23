package com.example.pushapplication.receiver;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class XiaoMiPushMessageReceiver extends PushMessageReceiver {

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {

    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {

    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {

    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {

    }

    @Override
    public void onRequirePermissions(Context context, String[] strings) {

    }
}
