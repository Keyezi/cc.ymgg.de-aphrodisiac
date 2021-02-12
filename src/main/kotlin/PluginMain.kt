package org.example.mirai.plugin

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info


object PluginMain : KotlinPlugin(
    JvmPluginDescription(id = "cc.ymgg.de-aphrodisiac", name = "Nd鉴黄", version = "2.0")
) {
    override fun onEnable() = logger.info { "插件已加载" }
    override fun onDisable() {
        logger.info("插件已被禁用")

    }

}

object Config : AutoSavePluginConfig("Configs") {
    val API_KEY by value<String>()
    val SECRET_KEY by value<String>()   //同上字面意思，引用百度API
    val DeleteMsg by value<Boolean>()   //是否撤回消息
    val Nospeak by value<Boolean>()     //是否禁言
    var NoSpeaktime by value<Int>()     //禁言时间


}
