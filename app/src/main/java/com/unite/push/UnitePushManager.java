package com.unite.push;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hms.utils.HMSPackageManager;
import com.meizu.cloud.pushsdk.PushManager;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//推送聚合
public class UnitePushManager {
    static final String TAG = "UnitePushManager";

    //这里添加各厂商的品牌
    static final String[] meiZuList = new String[]{"meizu", "noblue"};
    static final Set<String> meiZuSet = new HashSet<>(Arrays.asList(meiZuList));

    static final String[] xiaoMiList = new String[]{"xiaomi", "redmi"};
    static final Set<String> xiaoMiSet = new HashSet<>(Arrays.asList(xiaoMiList));

    static final String[] oppoList = new String[]{"oppo", "oneplus", "realme"};
    static final Set<String> oppoSet = new HashSet<>(Arrays.asList(oppoList));

    static final String[] vivoList = new String[]{"vivo", "iqoo"};
    static final Set<String> vivoSet = new HashSet<>(Arrays.asList(vivoList));

    static final String[] huaWeiList = new String[]{"huawei", "honor"};
    static final Set<String> huaWeiSet = new HashSet<>(Arrays.asList(huaWeiList));

    //各渠道推送初始化，vivo和华为会自动读取参数
    public static void initPushSdk(Context context) {
        Bundle meta = readPkgMeta(context);
        //把手机品牌都转成小写，判断当前的机型，然后调用对应的初始化
        String model = Build.MANUFACTURER.toLowerCase();
        if (vivoSet.contains(model)) {
            vivoPushSdk(context);
        } else if (oppoSet.contains(model)) {
            String oppoAppKey = meta.getString("oppoAppKey");
            String oppoAppSecret = meta.getString("oppoAppSecret");
            oppoPushSdk(context, oppoAppKey, oppoAppSecret);
        } else if (meiZuSet.contains(model)) {
            String meiZuAppId = meta.getString("meiZuAppId");
            String meiZuAppKey = meta.getString("meiZuAppKey");
            mzPushSdk(context, meiZuAppId, meiZuAppKey);
        } else if (xiaoMiSet.contains(model)) {
            String xiaoMiAppId = meta.getString("xiaoMiAppId");
            String xiaoMiAppKey = meta.getString("xiaoMiAppKey");
            xmPushSdk(context, xiaoMiAppId, xiaoMiAppKey);
        } else if (huaWeiSet.contains(model)) {
            hwPushSdk(context);
        } else {
            Log.e(TAG, "initPushSdk：不支持此机型：" + model);
        }
    }

    //各渠道推送反初始化
    public static void unInitPushSdk(Context context) {
        Bundle meta = readPkgMeta(context);
        String model = Build.MANUFACTURER.toLowerCase();
        if (vivoSet.contains(model)) {
            vivoUnInitPushSdk(context);
        } else if (oppoSet.contains(model)) {
            oppoUnInitPushSdk();
        } else if (meiZuSet.contains(model)) {
            String meiZuAppId = meta.getString("meiZuAppId");
            String meiZuAppKey = meta.getString("meiZuAppKey");
            mzUnInitPushSdk(context, meiZuAppId, meiZuAppKey);
        } else if (xiaoMiSet.contains(model)) {
            xmUnInitPushSdk(context);
        } else if (huaWeiSet.contains(model)) {
            hwUnInitPushSdk(context);
        } else {
            Log.e(TAG, "unInitPushSdk：不支持此机型：" + model);
        }
    }

    //获取清单文件中的meta
    private static Bundle readPkgMeta(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packName = context.getPackageName();
        final int flag = PackageManager.GET_META_DATA;
        Bundle metaData = null;

        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packName, flag);
            metaData = applicationInfo.metaData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metaData;
    }

    //检查配置参数，并格式化参数，去掉开头的"!"
    private static String[] checkPushArgs(String arg1, String arg2) {
        if (arg1 == null || arg2 == null) {
            throw new IllegalArgumentException("请检查并配置参数！");
        } else {
            String farg1 = arg1.replace("!", "");
            String farg2 = arg2.replace("!", "");
            return new String[]{farg1, farg2};
        }
    }

    /* 各渠道sdk的初始化和反初始化 */
    private static void mzPushSdk(Context context, String appId, String appKey) {
        String[] args = checkPushArgs(appId, appKey);
        PushManager.register(context, args[0], args[1]);
    }

    private static void mzUnInitPushSdk(Context context, String appId, String appKey) {
        String[] args = checkPushArgs(appId, appKey);
        PushManager.unRegister(context, args[0], args[1]);
    }

    private static void xmPushSdk(Context context, String appId, String appKey) {
        String[] args = checkPushArgs(appId, appKey);
        MiPushClient.registerPush(context, args[0], args[1]);
        MiPushClient.enablePush(context);
    }

    private static void xmUnInitPushSdk(Context context) {
        MiPushClient.unregisterPush(context);
        MiPushClient.disablePush(context);
    }

    /* 根据实际场景自行定制回调 */
    private static void oppoPushSdk(Context context, String appKey, String appSecret) {
        String[] args = checkPushArgs(appKey, appSecret);

        HeytapPushManager.init(context, false);
        ICallBackResultService callBack = new ICallBackResultService() {
            @Override
            public void onRegister(int i, String s) {

            }

            @Override
            public void onUnRegister(int i) {

            }

            @Override
            public void onSetPushTime(int i, String s) {

            }

            @Override
            public void onGetPushStatus(int i, int i1) {

            }

            @Override
            public void onGetNotificationStatus(int i, int i1) {

            }
        };
        HeytapPushManager.register(context, args[0], args[1], callBack);
    }

    private static void oppoUnInitPushSdk() {
        HeytapPushManager.unRegister();
    }

    private static void vivoPushSdk(Context context) {
        PushClient.getInstance(context).initialize();
        PushClient.getInstance(context).turnOnPush(new IPushActionListener() {
            @Override
            public void onStateChanged(int i) {

            }
        });
    }

    private static void vivoUnInitPushSdk(Context context) {
        PushClient.getInstance(context).turnOffPush(new IPushActionListener() {
            @Override
            public void onStateChanged(int i) {

            }
        });
    }

    private static void hwPushSdk(Context context) {
        Task<Void> task = HmsMessaging.getInstance(context).turnOnPush();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private static void hwUnInitPushSdk(Context context) {
        Task<Void> task = HmsMessaging.getInstance(context).turnOffPush();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    //获取pushId, 注意oppo是异步的
    public static String getPushId(Context context) {
        String model = Build.MANUFACTURER.toLowerCase();
        if (vivoSet.contains(model)) {
            return PushClient.getInstance(context).getRegId();
        } else if (oppoSet.contains(model)) {
            return HeytapPushManager.getRegisterID();
        } else if (meiZuSet.contains(model)) {
            return PushManager.getPushId(context);
        } else if (xiaoMiSet.contains(model)) {
            return MiPushClient.getRegId(context);
        } else if (huaWeiSet.contains(model)) {
            return HmsInstanceId.getInstance(context).getId();
        } else {
            Log.e(TAG, "getPushId：不支持此机型：" + model);
            return null;
        }
    }

    //添加主题或者别名
    public static void setTopicAlias(Context context, String topicAlias) {
        String model = Build.MANUFACTURER.toLowerCase();
        Bundle meta = readPkgMeta(context);

        if (vivoSet.contains(model)) {
            PushClient.getInstance(context).bindAlias(topicAlias, new IPushActionListener() {
                @Override
                public void onStateChanged(int i) {

                }
            });
        } else if (meiZuSet.contains(model)) {
            String meiZuAppId = meta.getString("meiZuAppId");
            String meiZuAppKey = meta.getString("meiZuAppKey");
            String regId = PushClient.getInstance(context).getRegId();
            PushManager.subScribeAlias(context, meiZuAppId, meiZuAppKey, regId, topicAlias);
        } else if (xiaoMiSet.contains(model)) {
            MiPushClient.setAlias(context, topicAlias, null);
        } else if (huaWeiSet.contains(model)) {
            HmsMessaging.getInstance(context).subscribe(topicAlias);
        } else if (oppoSet.contains(model)) {
            Log.e(TAG, "setTopicAlias：不支持此机型：" + model);
        } else {
            Log.e(TAG, "setTopicAlias：不支持此机型：" + model);
        }
    }

    //删除主题或者别名
    public static void unSetTopicAlias(Context context, String topicAlias) {
        String model = Build.MANUFACTURER.toLowerCase();
        Bundle meta = readPkgMeta(context);

        if (vivoSet.contains(model)) {
            PushClient.getInstance(context).unBindAlias(topicAlias, new IPushActionListener() {
                @Override
                public void onStateChanged(int i) {

                }
            });
        } else if (meiZuSet.contains(model)) {
            String meiZuAppId = meta.getString("meiZuAppId");
            String meiZuAppKey = meta.getString("meiZuAppKey");
            String regId = PushClient.getInstance(context).getRegId();
            PushManager.unSubScribeAlias(context, meiZuAppId, meiZuAppKey, regId, topicAlias);
        } else if (xiaoMiSet.contains(model)) {
            MiPushClient.unsetAlias(context, topicAlias, null);
        } else if (huaWeiSet.contains(model)) {
            HmsMessaging.getInstance(context).unsubscribe(topicAlias);
        } else if (oppoSet.contains(model)) {
            Log.e(TAG, "unSetTopicAlias：不支持此机型：" + model);
        } else {
            Log.e(TAG, "unSetTopicAlias：不支持此机型：" + model);
        }
    }

    //清除通知
    public static void clearNotification(Context context) {
        String model = Build.MANUFACTURER.toLowerCase();
        if (oppoSet.contains(model)) {
            HeytapPushManager.clearNotifications();
        } else if (meiZuSet.contains(model)) {
            PushManager.clearNotification(context);
        } else if (xiaoMiSet.contains(model)) {
            MiPushClient.clearNotification(context);
        } else if (vivoSet.contains(model)) {
            Log.e(TAG, "clearNotification：不支持此机型：" + model);
        } else if (huaWeiSet.contains(model)) {
            Log.e(TAG, "clearNotification：不支持此机型：" + model);
        } else {
            Log.e(TAG, "clearNotification：不支持此机型：" + model);
        }
    }

    //获取sdk版本
    public static String readVersion(Context context) {
        String model = Build.MANUFACTURER.toLowerCase();
        if (vivoSet.contains(model)) {
            return PushClient.getInstance(context).getVersion();
        } else if (oppoSet.contains(model)) {
            return HeytapPushManager.getSDKVersion();
        } else if (meiZuSet.contains(model)) {
            return PushManager.TAG;
        } else if (huaWeiSet.contains(model)) {
            int version = HMSPackageManager.getInstance(context).getHmsVersionCode();
            return String.valueOf(version);
        } else if (xiaoMiSet.contains(model)) {
            Log.e(TAG, "readVersion：不支持此机型：" + model);
            return null;
        } else {
            Log.e(TAG, "readVersion：不支持此机型：" + model);
            return null;
        }
    }
}
