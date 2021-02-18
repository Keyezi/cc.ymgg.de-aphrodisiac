@file:Suppress("unused", "RedundantSuspendModifier")

package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.MiraiPluginMain
import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

//todo:��ɳͷ���ָ��

object Regin : SimpleCommand(MiraiPluginMain, "regin", description = "����APIkey") {
    @Handler
    suspend fun CommandSender.handle(client_id: String, client_secret: String) {
        Config.API_KEY = client_id
        Config.SECRET_KEY = client_secret
        BaiduChecker.refreshAccessToken()
    }
}

object Refreshtoken : SimpleCommand(MiraiPluginMain, "refreshtoken", description = "ˢ��AccessToken") {
    @Handler
    suspend fun CommandSender.handle() {
        BaiduChecker.refreshAccessToken()
    }
}

object Changelevel : SimpleCommand(MiraiPluginMain, "changelevel", description = "�޸���˼���") {
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

object Changeloglevel : SimpleCommand(MiraiPluginMain, "changeloglevel", description = "�޸���־����") {
    @Handler
    suspend fun CommandSender.handle(level: Int) {
        if ((level > 6) or (level < 0)) {
            MiraiPluginMain.logger.warning(
                """[Log]������0-6����ȷֵ���ʵ�������־����ɼӿ촦���ٶȡ�
                |0:����  1:VERBOSE-����  2:DEBUG-����  3:INFO-��Ϣ 4:WARNING-���� 5:ERROR-���� 6:None-��
                |������ָ��LOG���ƹ�Log����������־
            """.trimMargin()
            )
            return
        }
        Log.level = level
        MiraiPluginMain.logger.info("[Log]�޸��ռǼ���Ϊ${level}�ɹ���")
    }
}

object ChangeMeasure : SimpleCommand(MiraiPluginMain, "changemeasure", description = """�޸ĳͷ���ʽ""") {
    @Handler
    suspend fun CommandSender.handle(DeleteMsg: Boolean, KickOut: Boolean, Mutetime: Int) {
        Config.DeleteMsg = DeleteMsg
        Config.KickOut = KickOut
        Config.Mutetime = Mutetime
        Log.i(
            """
            �Ѿ��޸ĳ���Ϊ��$DeleteMsg,
            �޸��߳�Ϊ��$KickOut,
            �޸Ľ���ʱ��Ϊ��$Mutetime(Minus)
            !:����ʱ������Ϊ0�򲻽���
            """, "�޸ĳͷ�"
        )
    }
}