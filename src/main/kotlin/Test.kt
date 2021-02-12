package org.example.mirai.plugin

import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder

val Compliance_level = 2 //确定
val Suspected_level = 3//疑似
var Check_level = 3
val ACCESSTOKEN = getAccessToken("ri8uiVXCtUlk8n4hkg1D66kc", "7avFtEUC80HMhrtxH0FFk3cGS04b5gGC")


fun getAccessToken(client_id: String, client_secret: String): String {
    val grant_type = "client_credentials"
    val httpClient = OkHttpClient()
    val requestBody = FormBody.Builder()
        .add("client_id", client_id)
        .add("client_secret", client_secret)
        .add("grant_type", grant_type)
        .build()
    val request = Request.Builder()
        .url("https://aip.baidubce.com/oauth/2.0/token")
        .post(requestBody)
        .build()
    val response = httpClient.newCall(request).execute()
    val responseData = response.body?.string()
    val json = Gson()
    val result = json.fromJson(responseData, ApiKeyRequest::class.java)
    return result.access_token
}

data class ApiKeyRequest(
    val refresh_token: String,
    val expires_in: Int,  //Seconds为单位
    val session_key: String,
    val access_token: String,
    val scope: String,
    val session_secret: String
)

/*
*
*
 */
fun checkmsg(Msg: String): Boolean {
    val Httpclient = OkHttpClient()
    val requestBody = FormBody.Builder()
        .add("text", URLEncoder.encode(Msg, "UTF-8"))
        .build()
    val request = Request.Builder()
        .url("https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined?access_token=$ACCESSTOKEN")
        .post(requestBody)
        .build()

    val responseData = Httpclient.newCall(request).execute().body!!.string() //假设访问一定成功,只要网络连接没毛病

    val json = JSON.parseObject(responseData)
    //TODO:完成对JSON的处理


    return true
}

data class CheckMsgRequest(
    val log_id: Long?,
    val conclusion: String?,
    val conclusionType: Int?,
    val data: Gson?,
    val error_code: Long?,
    val error_msg: String?

)

fun checkImageURL(url: String): Boolean {
    val Httpclient = OkHttpClient()
    val requestBody = FormBody.Builder()
        .add("imgUrl", URLEncoder.encode(url, "UTF-8")).build()
    val request = Request.Builder()
        .url("https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined?access_token=$ACCESSTOKEN")
        .post(requestBody).build()
    val responseData =
        Httpclient.newCall(request).execute().body!!.string() //假设访问一定成功,只要网络连接没毛病
    val json = JSON.parseObject(responseData)
    val resultLevel = json.getIntValue("conclusionType")
    return when (resultLevel) {
        1 -> false
        2 -> Check_level == Compliance_level    //不合规
        3 -> Check_level == Suspected_level     //疑似
        4 -> {
            //TODO:完成对访问错误的处理
            false
        }
        else -> {
            //TODO:完成对网络error的处理
            false
        }
    }
}


/*try {
} catch (e: Exception) {
    e.printStackTrace()
}*/