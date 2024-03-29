package com.manchuan.tools.activity.crypt

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ClipboardUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.kongzue.dialogx.dialogs.PopTip
import com.lxj.androidktx.core.md5Hmac
import com.manchuan.tools.databinding.ActivityHmacMd5Binding
import com.manchuan.tools.utils.UiUtils
import org.apache.commons.lang3.StringUtils

class HmacMD5Activity : AppCompatActivity() {

    private val cryptBinding by lazy {
        ActivityHmacMd5Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cryptBinding.root)
        setSupportActionBar(cryptBinding.toolbar)
        immersionBar {
            transparentBar()
            titleBar(cryptBinding.toolbar)
            statusBarDarkFont(!UiUtils.isDarkMode())
        }
        supportActionBar?.apply {
            title = "HMACMD5加解密"
            setDisplayHomeAsUpEnabled(true)
        }
        cryptBinding.materialbutton1.setOnClickListener {
            val string = cryptBinding.content.text.toString()
            val password = cryptBinding.content.text.toString()
            if (string.isBlank() || password.isBlank()) {
                PopTip.show("请输入内容和密钥")
            } else {
                runCatching {
                    cryptBinding.autocomplete1.setText(string.md5Hmac(password))
                }.onFailure {
                    PopTip.show("加密失败:${it.message}")
                }
            }
        }
        cryptBinding.imageview1.setOnClickListener {
            val string = cryptBinding.autocomplete1.text.toString()
            if (StringUtils.isBlank(string)) {
                PopTip.show("无内容")
            } else {
                PopTip.show("已复制")
                ClipboardUtils.copyText(string)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

}