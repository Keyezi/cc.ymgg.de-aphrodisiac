@file:Suppress("unused")

package cc.ymgg.deaphrodisac

import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.checker.MiraiSMsgChecker
import cc.ymgg.deaphrodisac.setting.Config
import cc.ymgg.deaphrodisac.setting.changelevel
import cc.ymgg.deaphrodisac.setting.refreshtoken
import cc.ymgg.deaphrodisac.setting.regin
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info


object MiraiPluginMain : KotlinPlugin(
    JvmPluginDescription(id = "cc.ymgg.de-aphrodisiac", name = "Nd鉴黄", version = "2.0")
) {
    override fun onEnable() {
        Config.reload()
        logger.info { "插件已加载" }
        BaiduChecker.refreshAccessToken()
        MiraiSMsgChecker.getevent()
        regin.register()
        refreshtoken.register()
        changelevel.register()
        if (!Config.first2use) {
            Log.i(
                """Nd鉴黄初次使用说明
                首先，您需要打入指令 /changelevel <Value>调整您需要的鉴定级别
                    <Value>：疑似 3 不合规 2
                其次您需要输入/regin <client_id> <client_secret> 来获取Accesstoken.
                    如果您没有以上两个密钥，请到 [https://cloud.baidu.com/product/imagecensoring] 获取
                接下来请关闭mirai,到Config/Nd鉴黄/Configs.yml内修改惩罚的相应操作，文件内有相应说明。
            """, "初次使用说明"
            )
            Config.first2use = true
        }

    }

    override fun onDisable() {
        regin.unregister()
        refreshtoken.unregister()
        changelevel.unregister()
    }
}

