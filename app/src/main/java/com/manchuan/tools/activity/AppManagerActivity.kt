package com.manchuan.tools.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.FileUtils
import com.drake.brv.annotaion.AnimationType
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.dylanc.longan.immerseStatusBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.widget.NavigationIconCompat
import com.kongzue.dialogx.dialogs.BottomMenu
import com.kongzue.dialogx.dialogs.PopTip
import com.kongzue.dialogx.interfaces.OnIconChangeCallBack
import com.manchuan.tools.R
import com.manchuan.tools.activity.app.SettingsActivity
import com.manchuan.tools.databinding.ActivityAppBinding
import com.manchuan.tools.items.AppItem
import com.manchuan.tools.utils.RootCmd.haveRoot
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import rikka.core.content.FileProvider
import java.io.DataOutputStream
import java.io.File
import java.util.Collections.sort

class AppManagerActivity : AppCompatActivity() {
    private lateinit var datas: MutableList<AppItem>
    private lateinit var allApps : MutableList<AppItem>
    private lateinit var userApp: MutableList<AppItem>
    private lateinit var systemApp: MutableList<AppItem>
    private lateinit var appManagerBinding: ActivityAppBinding
    private lateinit var rv_app: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appManagerBinding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(appManagerBinding.root)
        val toolbar = appManagerBinding.materialSearchBar.getToolbar()
        setSupportActionBar(toolbar)
        appManagerBinding.materialSearchBar.apply {
            navigationIconCompat = NavigationIconCompat.SEARCH
            setHint(getString(R.string.search))
            setOnClickListener {
                appManagerBinding.materialSearchBar.requestFocus()
            }
            setNavigationOnClickListener {
                appManagerBinding.materialSearchBar.requestFocus()
            }
        }
        supportActionBar?.apply {
            title = "????????????"
            setDisplayHomeAsUpEnabled(true)
        }
        immerseStatusBar()
        rv_app = appManagerBinding.rvApp
        datas = ArrayList()
        allApps  = ArrayList()
        systemApp = ArrayList()
        userApp = ArrayList()
        FastScrollerBuilder(rv_app).useMd2Style().build()
        rv_app.setHasFixedSize(true)
        rv_app.linear().setup {
            setAnimation(AnimationType.ALPHA)
            addType<AppItem>(R.layout.item_list_app)
            onClick(R.id.app) {
                startActivity(
                    Intent(
                        this@AppManagerActivity,
                        AppInformationActivity::class.java
                    ).putExtra("packageName", getModel<AppItem>().package_name)
                        .putExtra("appName", getModel<AppItem>().app_name)
                )
            }
            onLongClick(R.id.app) {
                onItemLongClickListeners(modelPosition)
            }
        }.models = datas
        scanLocalInstallAppList(packageManager)
    }

    private fun scanLocalInstallAppList(packageManager: PackageManager) {
        appManagerBinding.state.showLoading()
        if (allApps .size > 0) {
            allApps .clear()
        }
        Thread {
            try {
                @SuppressLint("QueryPermissionsNeeded") val packageInfos =
                    packageManager.getInstalledPackages(0)
                packageInfos.indices.forEach { flags ->
                    val packageInfo = packageInfos[flags]
                    val myAppInfo = AppItem(
                        packageInfo.applicationInfo.loadIcon(packageManager),
                        packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                        packageInfo.applicationInfo.packageName,
                        packageInfo.applicationInfo.sourceDir
                    )
                    allApps.add(myAppInfo)
                }
                handler.sendEmptyMessage(1)
            } catch (e: Exception) {
                e.printStackTrace()
                handler.sendEmptyMessage(-1)
            }
        }.start()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
    }

    private fun scanUserApp(packageManager: PackageManager) {
        appManagerBinding.state.showLoading()
        if (userApp.isNotEmpty()) {
            userApp.clear()
        }
        Thread {
            try {
                @SuppressLint("QueryPermissionsNeeded") val packageInfo =
                    packageManager.getInstalledPackages(0)
                for (i in packageInfo.indices) {
                    val packageInfo = packageInfo[i]
                    if (!AppUtils.isAppSystem(packageInfo.applicationInfo.packageName)
                    ) {
                        val myAppInfo = AppItem(
                            packageInfo.applicationInfo.loadIcon(packageManager),
                            packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                            packageInfo.applicationInfo.packageName,
                            packageInfo.applicationInfo.publicSourceDir
                        )
                        userApp.add(myAppInfo)
                    }
                }
                handler.sendEmptyMessage(2)
                //handler.sendEmptyMessage(1);
            } catch (e: Exception) {
                PopTip.show(e.message)
                e.printStackTrace()
                handler.sendEmptyMessage(-1)
            }
        }.start()
    }

    private fun scanSystemApp(packageManager: PackageManager) {
        appManagerBinding.state.showLoading()
        if (systemApp.size > 0) {
            systemApp.clear()
        }
        Thread {
            try {
                @SuppressLint("QueryPermissionsNeeded") val packageInfos =
                    packageManager.getInstalledPackages(0)
                for (i in packageInfos.indices) {
                    val packageInfo = packageInfos[i]
                    if (AppUtils.isAppSystem(packageInfo.applicationInfo.packageName)) {
                        val myAppInfo = AppItem(
                            packageInfo.applicationInfo.loadIcon(packageManager),
                            packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                            packageInfo.applicationInfo.packageName,
                            packageInfo.applicationInfo.sourceDir
                        )
                        systemApp.add(myAppInfo)
                    }
                }
                handler.sendEmptyMessage(3)
            } catch (e: Exception) {
                PopTip.show(e.message)
                e.printStackTrace()
                handler.sendEmptyMessage(-1)
            }
        }.start()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateNotify(list: List<AppItem>?) {
        datas.clear() //??????listview????????????
        if (list != null) {
            sort(list)
        }
        datas.addAll(list!!)
        appManagerBinding.state.showContent()
        //new MaterialScrollBar(this, rv_app, false);
    }

    fun run(command: String) {
        try {
            val ps = Runtime.getRuntime().exec("su")
            val writer = DataOutputStream(ps.outputStream)
            writer.writeBytes("$command\nexit\n")
            writer.flush()
            ps.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isEnabled(packageName: String?): Boolean {
        var state = false
        try {
            state = this.packageManager.getPackageInfo(packageName!!, 0).applicationInfo.enabled
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return state
    }

    /**
     * ??????????????????????????????
     * @param packageName
     * @param enabled
     */
    private fun setAppState(packageName: String, enabled: Boolean) {
        var command = "pm "
        command += if (enabled) {
            "enable $packageName"
        } else {
            "disable $packageName"
        }
        this.run(command)
    }

    override fun onDestroy() {
        super.onDestroy()
        datas.clear()
    }

    @SuppressLint("HandlerLeak")
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> updateNotify(allApps)
                2 -> updateNotify(userApp)
                3 -> updateNotify(systemApp)
                -1 -> {
                    //loadingDialog.dismiss();
                    PopTip.show("????????????...")
                    appManagerBinding.state.showError()
                }
                else -> {}
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.user_app -> try {
                scanUserApp(applicationContext.packageManager)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            R.id.system_app -> try {
                scanSystemApp(applicationContext.packageManager)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onItemLongClickListeners(position: Int) {
        val freeze: String = if (isEnabled(datas[position].package_name)) {
            "??????"
        } else if (!isEnabled(datas[position].package_name)) {
            "??????"
        } else {
            "??????"
        }
        BottomMenu.show(arrayOf("??????", "??????", freeze, "??????"))
            .setTitle("??? " + datas[position].app_name + " ????????????")
            .setOnIconChangeCallBack(object : OnIconChangeCallBack<BottomMenu>(true) {

                override fun getIcon(dialog: BottomMenu?, index: Int, menuText: String?): Int {
                    when (menuText) {
                        "??????" -> return R.drawable.ic_open_in_new
                        "??????" -> return R.drawable.ic_delete_outline_grey
                        "??????" -> return R.drawable.ic_snowflake_variant
                        "??????" -> return R.drawable.ic_snowflake_off
                        "??????" -> return R.drawable.ic_share_black_24dp
                    }
                    return 0
                }
            })
            .setOnMenuItemClickListener { dialog: BottomMenu?, text: CharSequence, index: Int ->
                when (text.toString()) {
                    "??????" -> AppUtils.launchApp(
                        datas[position].package_name
                    )
                    "??????" -> if (AppUtils.isAppSystem(datas[position].package_name) && !haveRoot()) {
                        MaterialAlertDialogBuilder(this@AppManagerActivity)
                            .setTitle("Warning")
                            .setMessage("??????App????????????")
                            .setPositiveButton("??????", null)
                            .create()
                            .show()
                    } else {
                        AppUtils.uninstallApp(datas[position].package_name)
                    }
                    "??????" -> if (haveRoot()) {
                        setAppState(datas[position].package_name, false)
                    } else {
                        PopTip.show("???ROOT??????")
                    }
                    "??????" -> if (haveRoot()) {
                        setAppState(datas[position].package_name, true)
                    } else {
                        PopTip.show("???ROOT??????")
                    }
                    "??????" -> {
                        //IntentUtils.
                        runCatching {
                            FileUtils.copy(
                                datas[position].apk_path,
                                Environment.getExternalStorageDirectory().absolutePath.toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + datas[position].app_name + ".apk"
                            );
                            PopTip.show("????????????:" + Environment.getExternalStorageDirectory().absolutePath.toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + datas[position].app_name + ".apk")
                            shareFile(
                                this@AppManagerActivity,
                                File(Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + datas[position].app_name + ".apk")
                            )
                        }.onFailure {
                            PopTip.show("????????????:" + it.message)
                        }
                    }
                }
                false
            }
    }

    companion object {
        fun shareFile(context: Context, uri: File) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_STREAM, FileProvider.getUriForFile(
                    context,
                    context.packageName + ".fileprovider",
                    uri
                )
            ) // ????????????????????????file??????
            when {
                uri.toString().endsWith(".gz") -> {
                    intent.type = "application/x-gzip" // ?????????gz??????gzip???mime
                }
                uri.toString().endsWith(".txt") -> {
                    intent.type = "text/plain" // ???????????????text/plain???mime
                }
                else -> {
                    intent.type = "application/octet-stream" // ???????????????????????????????????????????????????
                }
            }
            context.startActivity(intent) // ???????????????mail?????????????????????
        }
    }

}