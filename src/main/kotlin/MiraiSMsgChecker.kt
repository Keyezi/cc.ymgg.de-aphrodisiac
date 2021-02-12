package org.example.mirai.plugin

import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel

object MiraiSMsgChecker {
    fun getevent() {
        Log.v("Getting Events")

        MiraiPluginMain.globalEventChannel().subscribeAlways<GroupMessageEvent> {
            Log.v(message.toString())


        }
    }

    private fun doCheck() {

    }
}