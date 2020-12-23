# android第三方推送聚合

unitepush是将国内的第三方推送sdk聚合，目前支持的渠道有oppo，vivo，华为，小米还有魅族

## 配置

在各渠道开放平台申请账号和参数，测试的时候需要用到不同的机型

[oppo开放平台入口](https://id.heytap.com/index.html?callback=https%3A%2F%2Fopen.oppomobile.com)

[vivo开放平台入口](https://id.vivo.com.cn/?callback=https://dev.vivo.com.cn/documentCenter/doc/151&amp;_202012232108#!/access/login)

[华为开放平台入口](https://id1.cloud.huawei.com/CAS/portal/loginAuth.html?validated=true&amp;themeName=red&amp;service=https%3A%2F%2Foauth-login1.cloud.huawei.com%2Foauth2%2Fv2%2Flogin%3Faccess_type%3Doffline%26client_id%3D6099200%26display%3Dpage%26flowID%3Ddebdccc3-bc54-4c1c-869e-38e05a92f1ae%26h%3D1608728922.5920%26lang%3Dzh-cn%26redirect_uri%3Dhttps%253A%252F%252Fdeveloper.huawei.com%252Fconsumer%252Fcn%252Fdoc%252F%26response_type%3Dcode%26scope%3Dopenid%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fcountry%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fbase.profile%26state%3D5162038%26v%3D21621061d1c464afeb9b262a40a441584f275eab90381665adbe47cd6016df70&amp;loginChannel=89000003&amp;reqClientType=89&amp;lang=zh-cn&amp;clientID=6099200)

[小米开放平台入口](https://account.xiaomi.com/pass/serviceLogin?callback=https%3A%2F%2Faccount.xiaomi.com%2Fsts%3Fsign%3DZvAtJIzsDsFe60LdaPa76nNNP58%3D%26followup%3Dhttps%3A%2F%2Faccount.xiaomi.com%2Fpass%2Fauth%2Fsecurity%2Fhome%26sid%3Dpassport&sid=passport)

[魅族开放平台入口](https://login.flyme.cn/sso?appuri=https%3A%2F%2Fopen.flyme.cn%2Flogin&amp;useruri=https%3A%2F%2Fopen.flyme.cn&amp;sid=&amp;service=open&amp;autodirct=true)

在module的build.gradle里面加上各渠道的参数，主要是appId和appKey，注意前面加了一个感叹号以防止数字的类型转换，华为需要将配置文件[agconnect-services.json](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides-V5/android-integrating-sdk-0000001050040084-V5)放在工程目录里面

```gradle
manifestPlaceholders = [
     meiZuAppId: "!0000000000000",
     meiZuAppKey: "!rtofdlkjkdfsdjfkl",
     xiaoMiAppId: "!0000000000000",
     xiaoMiAppKey: "!rtofdlkjkdfsdjfkl",
     oppoAppKey: "!0000000000000",
     oppoAppSecret: "!rtofdlkjkdfsdjfkl",
     vivoAppId: "!0000000000000",
     vivoAppKey: "!rtofdlkjkdfsdjfkl",
]
```

## 基础功能

目前已完成的功能有初始化，反初始化，添加主题或者别名，删除主题或者别名，获取pushId，获取sdk版本，清除通知，都集成在UnitePushManager这个类里面

| 方法名            | 说明                                              | 参数列表和返回值          |
| ----------------- | ------------------------------------------------- | ------------------------- |
| initPushSdk       | 初始化推送，建议放在application的onCreate方法里面 | Context，无返回值         |
| unInitPushSdk     | 反初始化推送，关闭推送功能                        | Context，无返回值         |
| getPushId         | 获取PushId                                        | Context，返回String       |
| setTopicAlias     | 添加主题或者别名                                  | Context，String，无返回值 |
| unSetTopicAlias   | 删除主题或者别名                                  | Context，String，无返回值 |
| clearNotification | 清除通知                                          | Context，无返回值         |
| readVersion       | 获取sdk版本                                       | Context，返回String       |

继续完善😀😃
