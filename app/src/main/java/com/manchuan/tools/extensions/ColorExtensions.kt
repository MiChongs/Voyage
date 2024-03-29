package com.manchuan.tools.extensions

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout
import com.kongzue.dialogx.dialogs.PopTip
import com.manchuan.tools.R
import com.manchuan.tools.theme.untils.ColorUtil
import com.manchuan.tools.theme.untils.MaterialValueHelper
import com.manchuan.tools.theme.untils.ThemeStore
import com.manchuan.tools.theme.untils.ThemeUtil


fun Int.ripAlpha(): Int {
    return ColorUtil.stripAlpha(this)
}


fun Dialog.colorControlNormal() = resolveColor(android.R.attr.colorControlNormal)

fun Toolbar.backgroundTintList() {
    val surfaceColor = ThemeUtil.resolveColor(
        context, com.google.android.material.R.attr.colorSurface, Color.BLACK
    )
    val colorStateList = ColorStateList.valueOf(surfaceColor)
    backgroundTintList = colorStateList
}

fun Context.accentColor() = ThemeStore.accentColor(this)

fun Fragment.accentColor() = ThemeStore.accentColor(requireContext())

fun Context.surfaceColor() =
    resolveColor(com.google.android.material.R.attr.colorSurface, Color.WHITE)

fun Fragment.surfaceColor() =
    resolveColor(com.google.android.material.R.attr.colorSurface, Color.WHITE)

fun Context.colorPrimary() =
    resolveColor(com.google.android.material.R.attr.colorPrimary, Color.WHITE)

fun Context.colorPrimaryInverse() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryInverse, Color.WHITE)

fun Fragment.colorPrimaryInverse() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryInverse, Color.WHITE)

fun Fragment.colorPrimary() =
    resolveColor(com.google.android.material.R.attr.colorPrimary, Color.WHITE)

fun Context.colorPrimaryDark() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryDark, Color.WHITE)

fun Fragment.colorPrimaryDark() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryDark, Color.WHITE)

fun Context.surfaceColor(fallBackColor: Int) =
    resolveColor(com.google.android.material.R.attr.colorSurface, fallBackColor)

fun Fragment.surfaceColor(fallBackColor: Int) =
    resolveColor(com.google.android.material.R.attr.colorSurface, fallBackColor)

fun Context.textColorSecondary() = resolveColor(android.R.attr.textColorSecondary)

fun Fragment.textColorSecondary() = resolveColor(android.R.attr.textColorSecondary)

fun Context.colorControlNormal() = resolveColor(android.R.attr.colorControlNormal)

fun Fragment.colorControlNormal() = resolveColor(android.R.attr.colorControlNormal)

fun Context.errorColor() = resolveColor(com.google.android.material.R.attr.colorError)

fun Fragment.errorColor() = resolveColor(com.google.android.material.R.attr.colorError)

fun Context.onErrorColor() = resolveColor(com.google.android.material.R.attr.colorOnError)

fun Fragment.onErrorColor() = resolveColor(com.google.android.material.R.attr.colorOnError)

fun Context.colorBackground() = resolveColor(android.R.attr.colorBackground)

fun Fragment.colorBackground() = resolveColor(android.R.attr.colorBackground)

fun Context.windowBackground() = resolveColor(android.R.attr.windowBackground)

fun Fragment.windowBackground() = resolveColor(android.R.attr.windowBackground)

fun Context.textColorPrimary() = resolveColor(android.R.attr.textColorPrimary)

fun Fragment.textColorPrimary() = resolveColor(android.R.attr.textColorPrimary)

fun Context.defaultFooterColor() = resolveColor(R.attr.defaultFooterColor)

fun Fragment.defaultFooterColor() = resolveColor(R.attr.defaultFooterColor)

fun Context.colorPrimaryContainer() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryContainer)

fun Fragment.colorPrimaryContainer() =
    resolveColor(com.google.android.material.R.attr.colorPrimaryContainer)

fun Context.colorSecondaryContainer() =
    resolveColor(com.google.android.material.R.attr.colorSecondaryContainer)

fun Fragment.colorSecondaryContainer() =
    resolveColor(com.google.android.material.R.attr.colorSecondaryContainer)

fun Context.resolveColor(@AttrRes attr: Int, fallBackColor: Int = 0) =
    ThemeUtil.resolveColor(this, attr, fallBackColor)

fun Fragment.resolveColor(@AttrRes attr: Int, fallBackColor: Int = 0) =
    context?.let { ThemeUtil.resolveColor(it, attr, fallBackColor) } ?: 0

fun Dialog.resolveColor(@AttrRes attr: Int, fallBackColor: Int = 0) =
    ThemeUtil.resolveColor(context, attr, fallBackColor)

fun CheckBox.addAccentColor() {
    buttonTintList = ColorStateList.valueOf(ThemeStore.accentColor(context))
}

fun SeekBar.addAccentColor() {
    val colorState = ColorStateList.valueOf(ThemeStore.accentColor(context))
    progressTintList = colorState
    thumbTintList = colorState
}

fun Slider.addAccentColor() {
    val accentColor = ThemeStore.accentColor(context)
    trackActiveTintList = accentColor.colorStateList
    trackInactiveTintList = ColorUtil.withAlpha(accentColor, 0.5F).colorStateList
    thumbTintList = accentColor.colorStateList
}

fun Slider.accent() {
    val accentColor = context.accentColor()
    thumbTintList = accentColor.colorStateList
    trackActiveTintList = accentColor.colorStateList
    trackInactiveTintList = ColorUtil.withAlpha(accentColor, 0.1F).colorStateList
}

fun Button.accentTextColor() {
    setTextColor(context.accentColor())
}

fun MaterialButton.accentBackgroundColor() {
    backgroundTintList = ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_enabled), intArrayOf()),
        intArrayOf(context.accentColor(), context.accentColor().addAlpha(0.12f))
    )
}

fun MaterialButton.accentOutlineColor() {
    val color = ThemeStore.accentColor(context)
    val colorStateList = ColorStateList.valueOf(color)
    iconTint = colorStateList
    strokeColor = colorStateList
    setTextColor(colorStateList)
    rippleColor = colorStateList
}

fun MaterialButton.elevatedAccentColor() {
    val color = context.darkAccentColorVariant()
    rippleColor = ColorStateList.valueOf(color)
    setBackgroundColor(color)
    setTextColor(MaterialValueHelper.getPrimaryTextColor(context, color.isColorLight))
    iconTint = ColorStateList.valueOf(context.accentColor())
}

fun SeekBar.applyColor(@ColorInt color: Int) {
    thumbTintList = ColorStateList.valueOf(color)
    progressTintList = ColorStateList.valueOf(color)
    progressBackgroundTintList = ColorStateList.valueOf(color)
}

fun Slider.applyColor(@ColorInt color: Int) {
    ColorStateList.valueOf(color).run {
        thumbTintList = this
        trackActiveTintList = this
        trackInactiveTintList = ColorStateList.valueOf(color.addAlpha(0.1f))
        haloTintList = this
    }
}

fun ExtendedFloatingActionButton.accentColor() {
    val color = ThemeStore.accentColor(context)
    val textColor = MaterialValueHelper.getPrimaryTextColor(context, ColorUtil.isColorLight(color))
    val colorStateList = ColorStateList.valueOf(color)
    val textColorStateList = ColorStateList.valueOf(textColor)
    backgroundTintList = colorStateList
    setTextColor(textColorStateList)
    iconTint = textColorStateList
}

fun FloatingActionButton.accentColor() {
    val color = ThemeStore.accentColor(context)
    val textColor = MaterialValueHelper.getPrimaryTextColor(context, ColorUtil.isColorLight(color))
    backgroundTintList = ColorStateList.valueOf(color)
    imageTintList = ColorStateList.valueOf(textColor)
}

fun MaterialButton.applyColor(color: Int) {
    val backgroundColorStateList = ColorStateList.valueOf(color)
    val textColorColorStateList = ColorStateList.valueOf(
        MaterialValueHelper.getPrimaryTextColor(
            context, ColorUtil.isColorLight(color)
        )
    )
    backgroundTintList = backgroundColorStateList
    setTextColor(textColorColorStateList)
    iconTint = textColorColorStateList
}

fun MaterialButton.accentColor() {
    applyColor(ThemeStore.accentColor(context))
}

fun MaterialButton.applyOutlineColor(color: Int) {
    val colorStateList = ColorStateList.valueOf(color)
    iconTint = colorStateList
    strokeColor = colorStateList
    setTextColor(colorStateList)
    rippleColor = colorStateList
}

fun TextInputLayout.accentColor() {
    val accentColor = ThemeStore.accentColor(context)
    val colorState = ColorStateList.valueOf(accentColor)
    boxStrokeColor = accentColor
    defaultHintTextColor = colorState
    isHintAnimationEnabled = true
}

fun String.successToast() {
    PopTip.show(this).iconSuccess()
}

fun String.errorToast() {
    PopTip.show(this).iconError()
}

fun CircularProgressIndicator.accentColor() {
    val color = ThemeStore.accentColor(context)
    setIndicatorColor(color)
    trackColor = ColorUtil.withAlpha(color, 0.2f)
}

fun CircularProgressIndicator.applyColor(color: Int) {
    setIndicatorColor(color)
    trackColor = ColorUtil.withAlpha(color, 0.2f)
}

fun AppCompatImageView.accentColor(): Int = ThemeStore.accentColor(context)

fun AppCompatImageView.applyAccentColor() {
    setColorFilter(accentColor())
}

fun TextInputLayout.setTint(background: Boolean = true) {
    val accentColor = ThemeStore.accentColor(context)
    val colorState = ColorStateList.valueOf(accentColor)

    if (background) {
        backgroundTintList = colorState
        defaultHintTextColor = colorState
    } else {
        boxStrokeColor = accentColor
        defaultHintTextColor = colorState
        isHintAnimationEnabled = true
    }
}

@CheckResult
fun Drawable.tint(@ColorInt color: Int): Drawable {
    val tintedDrawable = DrawableCompat.wrap(this).mutate()
    setTint(color)
    return tintedDrawable
}

@CheckResult
fun Drawable.tint(context: Context, @ColorRes color: Int): Drawable =
    tint(context.getColorCompat(color))

@ColorInt
fun Context.getColorCompat(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@ColorInt
fun Context.darkAccentColor(): Int {
    return ColorUtils.blendARGB(
        accentColor(), surfaceColor(), if (surfaceColor().isColorLight) 0.9f else 0.92f
    )
}

@ColorInt
fun Context.darkAccentColorVariant(): Int {
    return ColorUtils.blendARGB(
        accentColor(), surfaceColor(), if (surfaceColor().isColorLight) 0.9f else 0.95f
    )
}

@ColorInt
fun Context.accentColorVariant(): Int {
    return if (surfaceColor().isColorLight) {
        accentColor().darkerColor
    } else {
        accentColor().lighterColor
    }
}

inline val @receiver:ColorInt Int.isColorLight
    get() = ColorUtil.isColorLight(this)

inline val @receiver:ColorInt Int.lightColor
    get() = ColorUtil.withAlpha(this, 0.5F)

inline val @receiver:ColorInt Int.lighterColor
    get() = ColorUtil.lightenColor(this)

inline val @receiver:ColorInt Int.darkerColor
    get() = ColorUtil.darkenColor(this)

inline val Int.colorStateList: ColorStateList
    get() = ColorStateList.valueOf(this)

fun @receiver:ColorInt Int.addAlpha(alpha: Float): Int {
    return ColorUtil.withAlpha(this, alpha)
}