@file:Suppress("unused")

package cc.ymgg.deaphrodisac

import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.checker.MiraiSMsgChecker
import cc.ymgg.deaphrodisac.setting.*
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.utils.info


object MiraiPluginMain : KotlinPlugin(
    @OptIn(ConsoleExperimentalApi::class)
    JvmPluginDescription.loadFromResource()
) {
    override fun onEnable() {
        Config.reload()
        logger.info { "插件已加载" }
        BaiduChecker.refreshAccessToken()
        MiraiSMsgChecker.getevent()
        Regin.register()
        Refreshtoken.register()
        Changelevel.register()
        Changeloglevel.register()
        ChangeMeasure.register()
        if (!Config.first2use) {
            @Suppress("SpellCheckingInspection")
            Log.i(
                """Nd鉴黄初次使用说明
                    |首先，您需要打入指令 /changelevel <Value>调整您需要的鉴定级别
                    |   <Value>：疑似 3 不合规 2
                    |其次您需要输入/regin <client_id> <client_secret> 来获取Accesstoken.    
                    |如果您没有以上两个密钥，请到 [https://cloud.baidu.com/product/imagecensoring] 获取
                    |接下来请输入/changemeasure <DeleteMsg> <KickOut> <Mutetime> 设置惩罚方式
                    |   <DeleteMsg>:撤回消息 <KickOut>：踢出被惩罚者 <Mutetime>禁言被惩罚者，填0为不禁言。
                    |   
                    |最后您可以使用/changeloglevel指令来设置本插件内的日志限制""",
                "初次使用说明"
            )
            Config.first2use = true
        }

    }

    override fun onDisable() {
        Regin.unregister()
        Refreshtoken.unregister()
        Changelevel.unregister()
        Changeloglevel.unregister()
        ChangeMeasure.unregister()
    }
}

