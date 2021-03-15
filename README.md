---
description: 原来是酷Q应用，现在迁移到Mirai原生中。
---

# 百度云鉴黄

## 初次使用：

首先您需要输入`/regin <client_id> <client_secret>` 来获取Accesstoken.

如果您没有以上两个密钥，请到 \[[https://cloud.baidu.com/product/imagecensoring](https://cloud.baidu.com/product/imagecensoring)\] 获取

接下来请输入`/changemeasure <DeleteMsg> <KickOut> <Mutetime>` 设置惩罚方式

```text
<DeleteMsg:Boolean>:撤回消息 
<KickOut:Boolean>：踢出被惩罚者 
<Mutetime:Int>禁言被惩罚者，填0为不禁言。
```

接着请继续设置检测限制级别。

```text
/changelevel <level:Int>
    //可输入值：[2：不合规] 或 [3:疑似]
```

当然，默认情况下管理是不会被开启的  
这时候您需要输入以下两种指令之一启用检测：

```text
[指令] /ToggleTextChk <Mode:Boolean>  切换文本检测
[指令] /ToggleImageChk <Mode:Boolean> 切换图片检测

<Mode:Boolean>:只能为true(真)或false(假)
```

最后您可以使用`/changeloglevel`指令来设置本插件内的日志限制

### 额外指令

```text
/refreshtoken
刷新token，因为运行一个月后token就会失效。
```

### 如果觉得好用请给个star，感谢

# ABC123
一只公刺猬来到草地上散步。正巧，一只粉色长耳兔也一蹦一跳地来到草地上散步。长耳兔看到刺猬那又细又弯的腿，忍不住嘲笑公刺猬说：“哟，这不是弯着腿的刺猬吗?你敢跟我比跑步吗?”公刺猬听了长耳兔嘲笑、讽刺的话语后，气愤地说：“别瞧不起我们刺猬，比就比，咱们中午田地里见!”
## ABC123
一只公刺猬来到草地上散步。正巧，一只粉色长耳兔也一蹦一跳地来到草地上散步。长耳兔看到刺猬那又细又弯的腿，忍不住嘲笑公刺猬说：“哟，这不是弯着腿的刺猬吗?你敢跟我比跑步吗?”公刺猬听了长耳兔嘲笑、讽刺的话语后，气愤地说：“别瞧不起我们刺猬，比就比，咱们中午田地里见!”
### ABC123
一只公刺猬来到草地上散步。正巧，一只粉色长耳兔也一蹦一跳地来到草地上散步。长耳兔看到刺猬那又细又弯的腿，忍不住嘲笑公刺猬说：“哟，这不是弯着腿的刺猬吗?你敢跟我比跑步吗?”公刺猬听了长耳兔嘲笑、讽刺的话语后，气愤地说：“别瞧不起我们刺猬，比就比，咱们中午田地里见!
