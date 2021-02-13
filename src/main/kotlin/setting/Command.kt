@file:Suppress("unused", "RedundantSuspendModifier")

package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.MiraiPluginMain
import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

object regin : SimpleCommand(MiraiPluginMain, "regin", description = "����APIkey") {
    @Handler
    suspend fun CommandSender.handle(client_id: String, client_secret: String) {
        Config.API_KEY = client_id
        Config.SECRET_KEY = client_secret
        BaiduChecker.refreshAccessToken()
    }
}

object refreshtoken : SimpleCommand(MiraiPluginMain, "refreshtoken", description = "ˢ��AccessToken") {
    @Handler
    suspend fun CommandSender.handle() {
        BaiduChecker.refreshAccessToken()
    }
}

object changelevel : SimpleCommand(MiraiPluginMain, "changelevel", description = "ˢ��AccessToken") {
    @Handler
    suspend fun CommandSender.handle(level: Int) {
        when (level) {
            2 -> Config.checklevel = level
            3 -> Config.checklevel = level
            else -> {
                Log.w("�������������[2�����Ϲ�]��[3:����]�������֡�")
            }
        }
    }

}
