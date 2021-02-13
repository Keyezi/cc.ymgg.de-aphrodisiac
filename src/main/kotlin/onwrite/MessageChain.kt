package cc.ymgg.deaphrodisac.onwrite

import cc.ymgg.deaphrodisac.checker.BaiduChecker
import cc.ymgg.deaphrodisac.checker.MiraiSMsgChecker
import cc.ymgg.deaphrodisac.tools.Log
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageChain

fun MessageChain.checkmsg(): Boolean {
    val deletedmsg = MiraiSMsgChecker.deleteMiraiImageCode(this)
    Log.v(deletedmsg, "DeletedMsg")
    return BaiduChecker.checkmsg(deletedmsg)
}


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
    for (i in imageList) {
        if (BaiduChecker.checkImageURL(i)) return true
    }
    return false
}