@file:Suppress("unused", "RedundantSuspendModifier")

package cc.ymgg.deaphrodisac.setting

import cc.ymgg.deaphrodisac.MiraiPluginMain
import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.setting.Config.checkImage
import cc.ymgg.deaphrodisac.setting.Config.checkText
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

fun regCmd() {
    Regin.register()
    Refreshtoken.register()
    Changelevel.register()
    Changeloglevel.register()
    ChangeMeasure.register()
    ToggleImageChk.register()
    ToggleTextChk.register()
}

fun unRegCmd() {
    Regin.unregister()
    Refreshtoken.unregister()
    Changelevel.unregister()
    Changeloglevel.unregister()
    ChangeMeasure.unregister()
    ToggleImageChk.unregister()
    ToggleTextChk.unregister()
}

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

object ToggleTextChk : SimpleCommand(MiraiPluginMain, "toggleTextChk", description = "�л�����ʶ��") {
    @Handler
    suspend fun CommandSender.handle(changeAble: Boolean?) {
        if (changeAble != null) {
            Log.i("����ʶ��ģʽ����Ϊ��$changeAble", "ToggleTextChk-hasVar")
            checkText = changeAble  //toggleTextChk
        } else {
            Log.i("δ�ṩ����������ʶ��ģʽ�л�Ϊ��$changeAble", "ToggleTextChk-null")
            checkText = !checkText //toggleTextChk true
        }

    }
}

object ToggleImageChk : SimpleCommand(MiraiPluginMain, "toggleImageChk", description = "�л�ͼƬʶ��") {
    @Handler
    suspend fun CommandSender.handle(changeAble: Boolean?) {
        if (changeAble != null) {
            Log.i("ͼƬʶ��ģʽ����Ϊ��$changeAble", "ToggleImageChk-hasVar")
            checkImage = changeAble
        } else {
            Log.i("δ�ṩ������ͼƬʶ��ģʽ�л�Ϊ��$changeAble", "ToggleImageChk-null")
            checkImage = !checkImage
        }
    }

}
