package org.example.mirai.plugin

object Log {
    private const val VERBOSE = 1
    private const val DEBUG = 2
    private const val INFO = 3
    private const val WARN = 4
    private const val ERROR = 5


    var level = VERBOSE
    val MiraiLog = MiraiPluginMain.logger

    fun v(msg: String, title: String = "") {
        if (level <= VERBOSE) MiraiLog.verbose(cw(msg, title))
    }

    fun d(msg: String, title: String = "") {
        if (level <= DEBUG) MiraiLog.debug(cw(msg, title))
    }

    fun i(msg: String, title: String = "") {
        if (level <= INFO) MiraiLog.info(cw(msg, title))
    }

    fun w(msg: String, title: String = "") {
        if (level <= WARN) MiraiLog.warning(cw(msg, title))
    }

    fun e(msg: String, title: String = "") {
        if (level <= ERROR) MiraiLog.error(cw(msg, title))
    }

    private fun cw(msg: String, title: String): String = if (title == "") msg else "[$title]$msg"
}