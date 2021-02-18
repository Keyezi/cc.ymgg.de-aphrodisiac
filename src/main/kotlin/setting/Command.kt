@file:Suppress("unused", "RedundantSuspendModifier")

package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.MiraiPluginMain
import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

//todo:完成惩罚的指令

object Regin : SimpleCommand(MiraiPluginMain, "regin", description = "设置APIkey") {
    @Handler
    suspend fun CommandSender.handle(client_id: String, client_secret: String) {
        Config.API_KEY = client_id
        Config.SECRET_KEY = client_secret
        BaiduChecker.refreshAccessToken()
    }
}

object Refreshtoken : SimpleCommand(MiraiPluginMain, "refreshtoken", description = "刷新AccessToken") {
    @Handler
    suspend fun CommandSender.handle() {
        BaiduChecker.refreshAccessToken()
    }
}

object Changelevel : SimpleCommand(MiraiPluginMain, "changelevel", description = "修改审核级别") {
    @Handler
    suspend fun CommandSender.handle(level: Int) {
        when (level) {
            2 -> Config.checklevel = level
            3 -> Config.checklevel = level
            else -> {
                Log.w("输入错误，请输入[2：不合规]或[3:疑似]两种数字。")
            }
        }
    }

}

object Changeloglevel : SimpleCommand(MiraiPluginMain, "changeloglevel", description = "修改日志级别") {
    @Handler
    suspend fun CommandSender.handle(level: Int) {
        if ((level > 6) or (level < 0)) {
            MiraiPluginMain.logger.warning(
                """[Log]请输入0-6的正确值。适当提升日志级别可加快处理速度。
                |0:所有  1:VERBOSE-测试  2:DEBUG-调试  3:INFO-信息 4:WARNING-警告 5:ERROR-错误 6:None-无
                |！：本指令LOG已绕过Log类进行输出日志
            """.trimMargin()
            )
            return
        }
        Log.level = level
        MiraiPluginMain.logger.info("[Log]修改日记级别为${level}成功。")
    }
}

object ChangeMeasure : SimpleCommand(MiraiPluginMain, "changemeasure", description = """修改惩罚方式""") {
    @Handler
    suspend fun CommandSender.handle(DeleteMsg: Boolean, KickOut: Boolean, Mutetime: Int) {
        Config.DeleteMsg = DeleteMsg
        Config.KickOut = KickOut
        Config.Mutetime = Mutetime
        Log.i(
            """
            已经修改撤回为：$DeleteMsg,
            修改踢出为：$KickOut,
            修改禁言时间为：$Mutetime(Minus)
            !:禁言时间设置为0则不禁言
            """, "修改惩罚"
        )
    }
}