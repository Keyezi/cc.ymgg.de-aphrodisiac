package cc.ymgg.deaphrodisac.checker

import cc.ymgg.deaphrodisac.http.PostUtil
import cc.ymgg.deaphrodisac.setting.Compliance_level
import cc.ymgg.deaphrodisac.setting.Config
import cc.ymgg.deaphrodisac.setting.Suspected_level
import cc.ymgg.deaphrodisac.setting.isNormalRunning
import cc.ymgg.deaphrodisac.tools.Log
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import image.getImageFromURL
import java.net.URL


object BaiduChecker {

    private lateinit var accessToken: String

    init {
        println()
        // refreshAccessToken()//TODO:导入config
    }

    /**外部方法，刷新AccessToken*/
    fun refreshAccessToken() {
        accessToken = getAccessToken(Config.API_KEY, Config.SECRET_KEY)
    }

    /**通过2个从cloud.baidu.com获取的API密钥获取AccessTOken*/
    private fun getAccessToken(client_id: String, client_secret: String): String {
        //       Log.i(client_id,"ClinetId_getAccessToken")
        //       Log.i(client_secret,"Clinetsecret_getAccessToken")
        when {
            client_id != "" -> {
            }
            client_secret != "" -> {
            }
            else -> {
                Log.w("client_id或client_secret为空，请及时填写并通过指令重新读取，否则无法使用。")
                isNormalRunning = false
                return ""
            }
        }
        val granttype = "client_credentials"
        val responseData = PostUtil().build {
            URL("https://aip.baidubce.com/oauth/2.0/token")
            add("client_id", client_id)
            add("client_secret", client_secret)
            add("grant_type", granttype)
        }

        val json = Gson()
        val result = json.fromJson(responseData, ApiKeyRequest::class.java)
        return if (result.access_token != "") {
            Log.i("ApiKey读取成功，KEY:${result.access_token}")
            isNormalRunning = true
            result.access_token
        } else {
            Log.w("ApiKey读取失败，以下是读取到的内容:$responseData", "GetApiKey")
            isNormalRunning = false
            ""
        }
    }

    data class ApiKeyRequest(
        val refresh_token: String,
        val expires_in: Int,  //Seconds为单位
        val session_key: String,
        val access_token: String,
        val scope: String,
        val session_secret: String
    )

    /** 输入消息字符串
     *
     * 当审核出现问题返回真
     *
     * 当审核无问题或者出现网络错误返回假，并关闭应用处理直到成功refreshAccessToken*/
    fun checkmsg(Msg: String): Boolean {
        Log.v(Msg, "checkmsg_Msg")
        val responseData = PostUtil().build {
            URL("https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined?access_token=$accessToken")
            add("text", Msg)
        }
        Log.v(responseData, "checkmsg_responseData")
        val json = JSON.parseObject(responseData)
        //TODO:完成对JSON错误处理
        return when (json.getIntValue("conclusionType")) {
            1 -> false
            2 -> Config.checklevel == Compliance_level    //不合规
            3 -> Config.checklevel == Suspected_level     //疑似
            4 -> {
                //TODO:完成对访问错误的处理2
                //isNormalRunning = false
                false

            }
            else -> {
                //TODO:完成对网络error的处理2
                isNormalRunning = false
                false
            }

        }
    }

    /**输入网络图片URL
     *
     * 当审核出现问题返回真
     *
     * 当审核无问题或者出现网络错误返回假，并关闭应用处理直到成功refreshAccessToken*/
    fun checkImageURL(url: String): Boolean {
        Log.v(url, "checkImageURL_url")
        val image = getImageFromURL(URL(url))
        // Log.v("${image.height} \n ${image.width}","ImageX")
        if ((image.height <= 128)
            or (image.height >= 4096)
            or (image.width <= 128)
            or (image.height >= 4096)
        ) {
            Log.v("URL:$url 大小不符合要求，已经忽略。", this.javaClass.name)
            return false
        }

        val responseData = PostUtil().build {
            URL("https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined?access_token=$accessToken")
            add("imgUrl", url)
        }
        Log.v(responseData, "checkImageURL_responsedata")


        val json = JSON.parseObject(responseData)//TODO:完成对JSON错误处理
        return when (json.getIntValue("conclusionType")) {
            1 -> false
            2 -> Config.checklevel == Compliance_level    //不合规
            3 -> Config.checklevel == Suspected_level     //疑似
            4 -> {
                Log.e("访问错误，以下是返回内容：$responseData", "checkImageURL_jsonvalue4")
                //TODO:完成对访问错误的处理
                //  isNormalRunning = false
                false
            }
            else -> {
                Log.e("访问错误，以下是返回内容：$responseData", "checkImageURL_jsonvalue?else")
                //TODO:完成对网络error的处理
                isNormalRunning = false
                false
            }
        }
    }


}




