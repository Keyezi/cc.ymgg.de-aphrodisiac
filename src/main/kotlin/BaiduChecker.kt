package org.example.mirai.plugin

import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import net.mamoe.mirai.message.data.MessageChain
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder


class BaiduChecker(client_id: String? = "", client_secret: String? = "") {
    private var Check_level = 2
    private var accessToken: String

    init {
        accessToken = getAccessToken(client_id!!, client_secret!!)
    }

    fun getAccessToken(client_id: String, client_secret: String): String {

        @Suppress("SENSELESS_COMPARISON")//沙口IDEA
        if ((client_id == null) or (client_secret == null)) {
            Log.w("client_id或client_secret为空，请及时填写并通过指令重新读取，否则无法使用。")
            isNormalRunning = false
            return ""
        }

        val Httpclient = OkHttpClient()
        val granttype = "client_credentials"
        val requestBody = FormBody.Builder()
            .add("client_id", client_id)
            .add("client_secret", client_secret)
            .add("grant_type", granttype)
            .build()
        val request = Request.Builder()
            .url("https://aip.baidubce.com/oauth/2.0/token")
            .post(requestBody)
            .build()
        val response = Httpclient.newCall(request).execute()
        val responseData = response.body?.string()

        val json = Gson()
        val result = json.fromJson(responseData, ApiKeyRequest::class.java)

        @Suppress("USELESS_ELVIS")
        return run {
            Log.i("ApiKey读取成功。")
            isNormalRunning = true
            result.access_token
        } ?: run {
            Log.w(
                "ApiKey读取失败，以下是读取到的内容:$responseData",
                "GetApiKey"
            )
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


    fun checkmsg(Msg: String): Boolean {
        val httpClient = OkHttpClient()
        val requestBody = FormBody.Builder()
            .add("text", URLEncoder.encode(Msg, "UTF-8"))
            .build()
        val request = Request.Builder()
            .url("https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined?access_token=$accessToken")
            .post(requestBody)
            .build()

        val responseData = httpClient.newCall(request).execute().body!!.string() //假设访问一定成功,只要网络连接没毛病
        println(responseData)
        val json = JSON.parseObject(responseData)
        //TODO:完成对JSON错误处理
        return when (json.getIntValue("conclusionType")) {
            1 -> false
            2 -> Check_level == Compliance_level    //不合规
            3 -> Check_level == Suspected_level     //疑似
            4 -> {
                //TODO:完成对访问错误的处理2
                isNormalRunning = false
                false

            }
            else -> {
                //TODO:完成对网络error的处理2
                false
            }

        }
    }

    fun checkImageURL(url: String): Boolean {
        val Httpclient = OkHttpClient()
        val requestBody = FormBody.Builder()
            .add("imgUrl", URLEncoder.encode(url, "UTF-8")).build()
        val request = Request.Builder()
            .url("https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined?access_token=$accessToken")
            .post(requestBody).build()
        val responseData =
            Httpclient.newCall(request).execute().body!!.string() //假设访问一定成功,只要网络连接没毛病
        val json = JSON.parseObject(responseData)//TODO:完成对JSON错误处理
        return when (json.getIntValue("conclusionType")) {
            1 -> false
            2 -> Check_level == Compliance_level    //不合规
            3 -> Check_level == Suspected_level     //疑似
            4 -> {
                //TODO:完成对访问错误的处理
                isNormalRunning = false
                false
            }
            else -> {
                //TODO:完成对网络error的处理
                false
            }
        }
    }

    fun DeleteMiraiImageCode(message: MessageChain): String {
        //TODO:完成函数名称所示内容
        return ""
    }

}




