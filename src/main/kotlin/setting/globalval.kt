package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.tools.Log
import okhttp3.OkHttpClient

val Compliance_level = 2  //���Ϲ�
val Suspected_level = 3   //����
//var runInMirai: Boolean = false

val httpClient = OkHttpClient()


var isNormalRunning = false
    set(T) {
        Log.v(T.toString(), "��������״̬���ã��ݲ�Ӱ���⣩")
        field = T
    }
    get() = true

