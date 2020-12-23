package com.example.pushapplication.receiver;

import android.content.Context;

import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

public class VivoMessageReceiver extends OpenClientPushMessageReceiver {

    public VivoMessageReceiver() {
        super();
    }

    @Override
    public void onReceiveRegId(Context context, String regId) {

    }

    @Override
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {

    }
}
