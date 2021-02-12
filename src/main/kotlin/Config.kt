package org.example.mirai.plugin

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value


object Config : AutoSavePluginConfig("Configs") {
    /**  百度API关键值client_secret */
    var API_KEY by value<String>()

    /**  百度API关键值client_id */
    var SECRET_KEY by value<String>()

    /**  是否撤回消息*/
    var DeleteMsg by value<Boolean>()

    /**是否禁言*/
    var Mute by value<Boolean>()

    /**禁言时间(以分钟计数)*/
    var Mutetime by value<Int>()

    /**是否踢出*/
    var KickOut by value<Boolean>()

    /***/


}