package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.tools.Log
import okhttp3.OkHttpClient

val Compliance_level = 2  //不合规
val Suspected_level = 3   //疑似
//var runInMirai: Boolean = false

val httpClient = OkHttpClient()


var isNormalRunning = false
    set(T) {
        Log.v(T.toString(), "正常运行状态设置（暂不影响检测）")
        field = T
    }
    get() = true

