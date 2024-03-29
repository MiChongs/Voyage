package com.manchuan.tools.activity.site

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.ClipboardUtils
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.drake.statusbar.immersive
import com.dylanc.longan.immerseStatusBar
import com.dylanc.longan.isAppDarkMode
import com.kongzue.dialogx.dialogs.PopTip
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.manchuan.tools.base.BaseActivity
import com.manchuan.tools.databinding.ActivityServerInfoBinding

class ServerInfoActivity : BaseActivity() {

    private val serverBinding by lazy {
        ActivityServerInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(serverBinding.root)
        setSupportActionBar(serverBinding.toolbar)
        immerseStatusBar(!isAppDarkMode)
        supportActionBar?.apply {
            title = "服务器信息"
            setDisplayHomeAsUpEnabled(true)
        }
        serverBinding.query.setOnClickListener {
            val telephone = serverBinding.url.text.toString()
            if (telephone.isEmpty()) {
                PopTip.show("请输入网站")
            } else {
                WaitDialog.show("查询中...")
                scopeNetLife {
                    val content = Get<String>("https://tenapi.cn/serverinfo/") {
                        param("url", telephone)
                    }.await()
                    val json = JSON.parseObject(content)
                    if (json.getIntValue("code") == 200) {
                        TipDialog.show("查询成功", WaitDialog.TYPE.SUCCESS)
                    } else {
                        TipDialog.show("无结果", WaitDialog.TYPE.WARNING)
                    }
                }.catch {
                    TipDialog.show("查询失败", WaitDialog.TYPE.ERROR)
                }
            }
        }
        serverBinding.imageview1.setOnClickListener {
            if (serverBinding.info.text.toString().isEmpty()) {
                PopTip.show("无内容")
            } else {
                ClipboardUtils.copyText(serverBinding.info.text.toString())
                PopTip.show("已复制")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}