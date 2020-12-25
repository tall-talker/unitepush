package com.unite.push.receiver;

import android.content.Context;

import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

//根据业务场景自定义回调
public class VivoMessageReceiver extends OpenClientPushMessageReceiver {

    @Override
    public void onReceiveRegId(Context context, String regId) {

    }

    @Override
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {

    }
}
