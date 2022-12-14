package com.manchuan.tools.genshin.appwidget

import android.annotation.SuppressLint
import android.content.Context
import com.manchuan.tools.genshin.appwidget.utils.CardUtils.mainUser
import com.manchuan.tools.genshin.bean.UserBean
import com.manchuan.tools.genshin.untils.Ds
import com.manchuan.tools.genshin.untils.MyCallBack
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
object CardRequest {
    lateinit var context: Context

    private const val APP_VERSION = "2.14.1"

    private val ds by lazy {
        Ds()
    }

    private val client by lazy {
        OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
            request.addHeader(
                "DS",
                ds.getDS("role_id=${mainUser.gameUid}&server=${mainUser.region}")
            )
            request.addHeader("Cookie", getCookie(mainUser))
            request.addHeader("x-rpc-client_type", "5")
            request.addHeader("x-rpc-app_version", APP_VERSION)
            it.proceed(request.build())
        }.retryOnConnectionFailure(true)
            .readTimeout(3L, TimeUnit.MINUTES)
            .build()
    }

    fun getMonthLedger(block: (JSONObject) -> Unit) {
        val month = Calendar.getInstance().get(Calendar.MONTH)
        client.newCall(
            Request.Builder().url(getMonthLedgerUrl(0, mainUser.gameUid, mainUser.region)).build()
        ).enqueue(MyCallBack {
            block(it)
        })
    }

    fun getDailyNote(block: (JSONObject) -> Unit) {
        client.newCall(
            Request.Builder().url(getDailyNoteUrl(mainUser.gameUid, mainUser.region)).build()
        ).enqueue(MyCallBack {
            block(it)
        })
    }

    private fun getMonthLedgerUrl(month: Int = 0, gameUID: String, server: String): String {
        return "https://hk4e-api.mihoyo.com/event/ys_ledger/monthInfo?month=$month&bind_uid=$gameUID&bind_region=$server&bbs_presentation_style=fullscreen&bbs_auth+required=true&mys_source=GameRecord"
    }

    private fun getDailyNoteUrl(gameUID: String, server: String): String {
        return "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/dailyNote?role_id=$gameUID&server=$server"
    }

    private fun getCookie(user: UserBean): String {
        return "ltuid=${user.loginUid};ltoken=${user.lToken};account_id=${user.loginUid};cookie_token=${user.cookieToken}"
    }

}