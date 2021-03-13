package cc.ymgg.deaphrodisac.checker

import cc.ymgg.deaphrodisac.MiraiPluginMain
import cc.ymgg.deaphrodisac.onwrite.checkImage
import cc.ymgg.deaphrodisac.onwrite.checkmsg
import cc.ymgg.deaphrodisac.setting.Config
import cc.ymgg.deaphrodisac.tools.Log
import net.mamoe.mirai.contact.getMember
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource.Key.recall
import net.mamoe.mirai.message.data.PlainText

object MiraiSMsgChecker {
    fun getevent() {
        Log.v("Getting Events")

        MiraiPluginMain.globalEventChannel().subscribeAlways<GroupMessageEvent> {
            doCheck(this)
        }
    }

    private suspend fun doCheck(groupmsg: GroupMessageEvent) {
       // if (!isNormalRunning) return
        val msg = groupmsg.message
        if (msg.checkImage() or msg.checkmsg()) {
            doMeasure(groupmsg)
        } else {
            Log.v("检查完成，无事发生", "Docheck")
            return
        }
    }

    /**删除mirai码的文字真是太好啦*/
    fun deleteMiraiImageCode(message: MessageChain): String =
        message.joinToString { if (it is PlainText) it.toString() else "" }

    private suspend fun doMeasure(groupmsg: GroupMessageEvent) {
        val sender = groupmsg.sender
        Log.i("群员${groupmsg.senderName}发送违规信息遭到惩罚")
        if (Config.Mutetime != 0) sender.mute(Config.Mutetime * 60)
        if (Config.DeleteMsg) groupmsg.message.recall()
        if (Config.KickOut) groupmsg.group.getMember(sender.id)?.kick("消息检测惩罚")
        //if (Config.KickOut) groupmsg.sender
    }

}


