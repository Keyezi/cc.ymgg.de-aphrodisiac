package org.example.mirai.plugin

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value


object Config : AutoSavePluginConfig("Configs") {
    /**  �ٶ�API�ؼ�ֵclient_secret */
    var API_KEY by value<String>()

    /**  �ٶ�API�ؼ�ֵclient_id */
    var SECRET_KEY by value<String>()

    /**  �Ƿ񳷻���Ϣ*/
    var DeleteMsg by value<Boolean>()

    /**�Ƿ����*/
    var Mute by value<Boolean>()

    /**����ʱ��(�Է��Ӽ���)*/
    var Mutetime by value<Int>()

    /**�Ƿ��߳�*/
    var KickOut by value<Boolean>()

    /***/


}