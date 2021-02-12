package org.example.mirai.plugin

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info


object MiraiPluginMain : KotlinPlugin(
    JvmPluginDescription(id = "cc.ymgg.de-aphrodisiac", name = "Nd鉴黄", version = "2.0")
) {
    override fun onEnable() {
        MiraiSMsgChecker.getevent()
        logger.info { "插件已加载" }
    }


}

