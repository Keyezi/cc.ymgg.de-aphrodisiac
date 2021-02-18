package cc.ymgg.deaphrodisac.setting

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value


object Config : AutoSavePluginConfig("Configs") {
    /**  �ٶ�API�ؼ�ֵclient_secret */
    @ValueDescription("�ٶ�API�ؼ�ֵclient_secret")
    var API_KEY by value<String>()

    /**  �ٶ�API�ؼ�ֵclient_id */
    @ValueDescription("�ٶ�API�ؼ�ֵclient_id")
    var SECRET_KEY by value<String>()

    /**  �Ƿ񳷻���Ϣ*/
    @ValueDescription("�Ƿ񳷻���Ϣ")
    var DeleteMsg by value<Boolean>()

    /**�Ƿ���ԣ�������*/
    @ValueDescription("�Ƿ���ԣ�������")
    var Mute by value<Boolean>()

    /**����ʱ��(�Է��Ӽ���)*/
    @ValueDescription("����ʱ��(�Է��Ӽ���)")
    var Mutetime by value<Int>()

    /**�Ƿ��߳�*/
    @ValueDescription("�Ƿ��߳�")
    var KickOut by value<Boolean>()

    /**��鼶��*/
    @ValueDescription("��鼶��")
    var checklevel by value<Int>()

    @ValueDescription("��һ��ʹ�ã������Ҫ�鿴Ӧ��������ʾɾ�������ݼ���")
    var first2use by value<Boolean>()


}