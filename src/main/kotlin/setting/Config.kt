package cc.ymgg.deaphrodisac.setting

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value


object Config : AutoSavePluginConfig("Configs") {
    /**  百度API关键值client_secret */
    @ValueDescription("百度API关键值client_secret")
    var API_KEY by value<String>()

    /**  百度API关键值client_id */
    @ValueDescription("百度API关键值client_id")
    var SECRET_KEY by value<String>()

    /**  是否撤回消息*/
    @ValueDescription("是否撤回消息")
    var DeleteMsg by value<Boolean>()

    /**是否禁言（废弃）*/
    @ValueDescription("是否禁言（废弃）")
    var Mute by value<Boolean>()

    /**禁言时间(以分钟计数)*/
    @ValueDescription("禁言时间(以分钟计数)")
    var Mutetime by value<Int>()

    /**是否踢出*/
    @ValueDescription("是否踢出")
    var KickOut by value<Boolean>()

    /**检查级别*/
    @ValueDescription("检查级别")
    var checklevel by value<Int>()

    @ValueDescription("第一次使用，如果需要查看应用启动提示删除此内容即可")
    var first2use by value<Boolean>()


}