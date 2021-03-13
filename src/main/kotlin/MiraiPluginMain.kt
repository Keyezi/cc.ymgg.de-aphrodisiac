@file:Suppress("unused")

package cc.ymgg.deaphrodisac

import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.checker.MiraiSMsgChecker
import cc.ymgg.deaphrodisac.setting.Config
import cc.ymgg.deaphrodisac.setting.regCmd
import cc.ymgg.deaphrodisac.setting.unRegCmd
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.utils.info


@ConsoleExperimentalApi
object MiraiPluginMain : KotlinPlugin(
    JvmPluginDescription.loadFromResource()
) {
    override fun onEnable() {
        Config.reload()
        logger.info { "插件已加载" }
        BaiduChecker.refreshAccessToken()
        MiraiSMsgChecker.getevent()
        regCmd()
        if (!Config.first2use) {
            @Suppress("SpellCheckingInspection")
            Log.i(
                """Nd鉴黄初次使用说明
                    |请访问https://github.com/YmggDEV/cc.ymgg.de-aphrodisiac 了解如何使用~
                    |   ▄▄▄▄▄▄▄   ▄   ▄▄  ▄▄  ▄▄▄▄▄▄▄  
                    |   █ ▄▄▄ █ █▀▀   █▀▀▄▀▄█ █ ▄▄▄ █  
                    |   █ ███ █ ▄█▄ ▄ ▄█▄▀█▀  █ ███ █  
                    |   █▄▄▄▄▄█ █ █ █▀▄▀▄ █▀▄ █▄▄▄▄▄█  
                    |   ▄▄ ▄  ▄▄▀ ▀▄▄▄█▄▄█ ▄▀ ▄▄▄ ▄▄   
                    |   ███▄ ▀▄ ▀▀  ▀█▄ ▄▀ ▄▀▄▀ ▄█▄▄▀  
                    |   ▄█ ▀ ▄▄ ▀▀█ ▄█  █ ▀█▀▄▄ ▀▄▀█▄  
                    |   ▄▀▄█  ▄  ▀▄▄▀ ▀  ▀▀ █▀▄▄▄▄▄▄▄  
                    |   █ █▄▄█▄███▀█▄▀  ▀█▄ █▀▀▄ ▀ █  
                    |   ▄ ▀▄▄▀▄██▄▄▀▀ █ ▀█  ▀█▀█▄▀ ▄█  
                    |   ▄ █  ▄▄▀▀▀▀▄ ▄▄█▄▀▀▄██▄██ ▄▀▀  
                    |   ▄▄▄▄▄▄▄ █▀▀ ▀▀█ ██▄▀█ ▄ █ ▀█▀  
                    |   █ ▄▄▄ █  ▀█▄▄█▀█ ▄▀ █▄▄▄█▀▀ ▄  
                    |   █ ███ █ ▀▄▄▄██▄ ▄▀▄██ ▀▀███ ▄  
                    |   █▄▄▄▄▄█ █▀ ▀█▀▀▄▀██▀██▄▄█▄ █   
                    |   
                    |   如遇无法扫描可尝试更换字体！
                    |   
                """.trimMargin(),

                "初次使用说明"

            )
            Config.first2use = true
        }

    }

    override fun onDisable() {
        unRegCmd()
    }
}


