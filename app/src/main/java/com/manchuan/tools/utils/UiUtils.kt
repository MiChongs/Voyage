@file:Suppress("DEPRECATION")
package com.manchuan.tools.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDelegate
import com.manchuan.tools.manager.NOT_MEASURED
import com.manchuan.tools.manager.SystemBarManager


object UiUtils {

    fun setSystemBarStyle(window: Window, needLightStatusBar: Boolean = true) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (!isDarkMode()) {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && needLightStatusBar) {
                window.decorView.systemUiVisibility = (
                        window.decorView.systemUiVisibility
                                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            if ((window.decorView.rootWindowInsets?.systemWindowInsetBottom ?: 0) >= Resources.getSystem().displayMetrics.density * 40) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    window.decorView.systemUiVisibility = (
                            window.decorView.systemUiVisibility
                                    or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
                }
            }
        }
        setSystemBarTransparent(window)
    }

    fun setSystemBarTransparent(window: Window) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }
    }

    fun isDarkMode(): Boolean {
        return when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, AppCompatDelegate.MODE_NIGHT_UNSPECIFIED -> isDarkModeOnSystem()
            else -> false
        }
    }

    fun isDarkModeOnSystem(): Boolean {
        return when (Utility.getAppContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }
    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    fun getStatusBarHeight(): Int {
        return if (SystemBarManager.statusBarSize == NOT_MEASURED) {
            val resources: Resources = Utility.getAppContext().resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            resources.getDimensionPixelSize(resourceId)
        } else {
            SystemBarManager.statusBarSize
        }
    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    fun getStatusBarHeightPx(): Int {
        val resources: Resources = Utility.getAppContext().resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimension(resourceId).toInt()
    }

    @Deprecated("Use SystemBarManager#navigationBarSize instead")
    fun getNavBarHeight(windowManager: WindowManager): Int {
        val windowHeight = windowManager.defaultDisplay.height
        var windowFullHeight = 0
        try {
            val dm = DisplayMetrics()
            val klass = Class.forName("android.view.Display")
            val method = klass.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(windowManager.defaultDisplay, dm)
            windowFullHeight = dm.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return (windowFullHeight - windowHeight - getStatusBarHeight()).coerceAtLeast(0)
    }

    fun getActionBarSize(activity: Activity): Int {
        val tv = TypedValue()

        return if (activity.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(
                tv.data,
                activity.resources.displayMetrics
            )
        } else {
            0
        }
    }

    fun addTransitionableTarget(activity: Activity,targetActivity: Class<*>,@IdRes id: Int) {
        val target: View = activity.findViewById(id)
        target.setOnClickListener { sharedElement: View ->
            startEndActivity(activity,targetActivity,sharedElement)
        }
    }

    fun startEndActivity(activity: Activity,targetActivity: Class<*>,sharedElement: View) {
        val intent = Intent(activity, targetActivity::class.java)
        val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
            activity, sharedElement, "shared_element_end_root")
        activity.startActivity(intent, options.toBundle())
    }

}