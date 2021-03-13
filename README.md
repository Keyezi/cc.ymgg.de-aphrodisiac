# 百度云鉴黄

原来是酷Q应用，现在迁移到Mirai原生中。

# 使用说明

Nd鉴黄初次使用说明

首先，您需要打入指令 /changelevel <Value>调整您需要的鉴定级别

       <Value>：疑似 3 不合规 2

其次您需要输入/regin <client_id> <client_secret> 来获取Accesstoken.    
如果您没有以上两个密钥，请到 [ https://cloud.baidu.com/product/imagecensoring ] 获取

接下来请输入/changemeasure <DeleteMsg> <KickOut> <Mutetime> 设置惩罚方式

<DeleteMsg>:撤回消息 <KickOut>：踢出被惩罚者 <Mutetime>禁言被惩罚者，填0为不禁言。

最后您可以使用/changeloglevel指令来设置本插件内的日志限制