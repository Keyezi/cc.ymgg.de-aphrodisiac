package org.example.mirai.plugin

import cc.ymgg.deaphrodisac.MiraiPluginMain
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import net.mamoe.mirai.console.util.ConsoleExperimentalApi


@ConsoleExperimentalApi
suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

    MiraiPluginMain.load()
    //Config.API_KEY = "ri8uiVXCtUlk8n4hkg1D66kc"
    //Config.SECRET_KEY = "7avFtEUC80HMhrtxH0FFk3cGS04b5gGC"
    MiraiPluginMain.enable()

    @Suppress("UNUSED_VARIABLE")
    val bot = MiraiConsole.addBot(246463104, "Œ“≤ª «√‹¬Î123") {
        fileBasedDeviceInfo()
    }.alsoLogin()
    // bot.getGroup(1041524354)?.settings?.isMuteAll = true


    MiraiConsole.job.join()

}

