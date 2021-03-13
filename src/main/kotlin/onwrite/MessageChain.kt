package cc.ymgg.deaphrodisac.onwrite

import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.checker.MiraiSMsgChecker
import cc.ymgg.deaphrodisac.setting.Config
import cc.ymgg.deaphrodisac.tools.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageChain

fun MessageChain.checkmsg(): Boolean {
    if (!Config.checkText) return false
    val deletedmsg = MiraiSMsgChecker.deleteMiraiImageCode(this)
    Log.v(deletedmsg, "DeletedMsg")
    return BaiduChecker.checkmsg(deletedmsg)
}

fun MessageChain.checkImage(): Boolean {
    if (!Config.checkImage) return false
    for (msgr in this) {
        if (msgr is Image) {
            if (runBlocking {
                    val url = async { msgr.queryUrl() }
                    BaiduChecker.checkImageURL(url.await())
                }) return true
        }
    }
    return false
}


/*什么阴间方法😅
fun MessageChain.checkImage(): Boolean {
    val imageList = mutableListOf<String>()
    joinToString {
        when (it) {
            is Image -> runBlocking {
                val a = it.queryUrl()
                imageList.add(a)
                ""
            }
            else -> ""
        }
    }
    Log.v(imageList.toString(), "imagelist")
    if (imageList.isEmpty()) return false
    for (i in imageList) {
        if (BaiduChecker.checkImageURL(i)) return true
    }
    return false
}*/