package com.manchuan.tools.ui.functions

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.drake.brv.utils.setup
import com.drake.engine.base.EngineFragment
import com.dylanc.longan.startActivity
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.itxca.spannablex.spannable
import com.kongzue.dialogx.dialogs.BottomMenu
import com.kongzue.dialogx.dialogs.PopTip
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener
import com.lxj.androidktx.core.animateGone
import com.lxj.androidktx.core.animateVisible
import com.manchuan.tools.R
import com.manchuan.tools.activity.*
import com.manchuan.tools.activity.touch.MainActivity
import com.manchuan.tools.activity.video.*
import com.manchuan.tools.databinding.FragmentGalleryBinding
import com.manchuan.tools.extensions.colorPrimary
import com.manchuan.tools.interfaces.HardwarePresenter
import com.manchuan.tools.model.FlexTagModel
import com.manchuan.tools.utils.CommonFunctions
import com.manchuan.tools.utils.RootCmd.haveRoot
import com.manchuan.tools.utils.RootCmd.runRootCommand
import com.manchuan.tools.utils.SettingsLoader
import org.koin.android.ext.android.inject

class GalleryFragment : EngineFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private var image_text: TextView? = null
    private var system_text: TextView? = null
    private var decode_text: TextView? = null
    private var other_text: TextView? = null
    private var web_text: TextView? = null
    private var card2: MaterialCardView? = null
    private var card3: MaterialCardView? = null
    private var card4: MaterialCardView? = null
    private var card5: MaterialCardView? = null
    private var card6: MaterialCardView? = null
    private var card7: MaterialCardView? = null
    private var states_t = 1
    private var states_tr = 1
    private var states_f = 1
    private var states_fi = 1
    private var states_s = 1
    private var states_se = 1
    private var states_o = 1
    private var states_n = 1
    private var img1: MaterialButton? = null
    private var img2: MaterialButton? = null
    private var img3: MaterialButton? = null
    private var img4: MaterialButton? = null
    private var img5: MaterialButton? = null
    private var img6: MaterialButton? = null
    private var img7: MaterialButton? = null
    private var img9: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }


    private fun openImage(view: View?) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 90f, 0f)
        objectAnimator.duration = 300
        objectAnimator.start()
    }

    private fun closeImage(view: View?) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 90f)
        objectAnimator.duration = 300
        objectAnimator.start()
    }

    private fun closeNoAnimate(view: View?) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 90f)
        objectAnimator.duration = 0
        objectAnimator.start()
    }

    override fun initData() {

    }

    private val hardwareFloat: HardwarePresenter by inject()

    override fun initView() {
        binding.layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        image_text = binding.imageText
        system_text = binding.systemText
        decode_text = binding.decodeText
        other_text = binding.otherText
        web_text = binding.webText
        img1 = binding.img1
        img2 = binding.img2
        img3 = binding.img3
        img4 = binding.img4
        img5 = binding.img5
        img6 = binding.img6
        img7 = binding.img7
        img9 = binding.img9
        val card1: MaterialCardView = binding.card1
        card2 = binding.card2
        card3 = binding.card3
        card4 = binding.card4
        card5 = binding.card5
        card6 = binding.card6
        card7 = binding.card7
        //????????????
        closeNoAnimate(img1)
        closeNoAnimate(img2)
        closeNoAnimate(img3)
        closeNoAnimate(img4)
        closeNoAnimate(img5)
        closeNoAnimate(img6)
        closeNoAnimate(img7)
        closeNoAnimate(img9)
        card1.setOnClickListener {
            if (states_o == 1) {
                binding.dailyLabelView.visibility = View.GONE
                openImage(img1)
                //img1.startAnimation(reverseAnimate);
                states_o = 2
            } else if (states_o == 2) {
                binding.dailyLabelView.visibility = View.VISIBLE
                closeImage(img1)
                //img1.startAnimation(concurAnimate);
                states_o = 1
            }
        }
        with(card2) {
            this?.setOnClickListener {
                if (states_t == 1) {
                    binding.imageLabelView.visibility = View.GONE
                    openImage(img2)
                    states_t = 2
                } else if (states_t == 2) {
                    binding.imageLabelView.visibility = View.VISIBLE
                    closeImage(img2)
                    states_t = 1
                }
            }
        }
        with(card3) {
            this?.setOnClickListener {
                if (states_tr == 1) {
                    binding.systemLabelView.visibility = View.GONE
                    openImage(img3)
                    //img1.startAnimation(reverseAnimate);
                    states_tr = 2
                } else if (states_tr == 2) {
                    binding.systemLabelView.visibility = View.VISIBLE
                    closeImage(img3)
                    //img1.startAnimation(concurAnimate);
                    states_tr = 1
                }
            }
        }
        with(card4) {
            this?.setOnClickListener {
                if (states_f == 1) {
                    binding.codeLabelView.visibility = View.GONE
                    openImage(img4)
                    //img1.startAnimation(reverseAnimate);
                    states_f = 2
                } else if (states_f == 2) {
                    binding.codeLabelView.visibility = View.VISIBLE
                    closeImage(img4)
                    //img1.startAnimation(concurAnimate);
                    states_f = 1
                }
            }
        }
        with(card5) {
            this?.setOnClickListener {
                when (states_fi) {
                    1 -> {
                        binding.otherLabelView.visibility = View.GONE
                        openImage(img5)
                        //img1.startAnimation(reverseAnimate);
                        states_fi = 2
                    }
                    2 -> {
                        binding.otherLabelView.visibility = View.VISIBLE
                        closeImage(img5)
                        //img1.startAnimation(concurAnimate);
                        states_fi = 1
                    }
                }
            }
        }
        with(card6) {
            this?.setOnClickListener {
                when (states_s) {
                    1 -> {
                        binding.webLabelView.visibility = View.GONE
                        openImage(img6)
                        states_s = 2
                    }
                    2 -> {
                        binding.webLabelView.visibility = View.VISIBLE
                        closeImage(img6)
                        states_s = 1
                    }
                }
            }
        }
        with(card7) {
            this?.setOnClickListener {
                when (states_se) {
                    1 -> {
                        binding.serverLabelView.animateGone()
                        openImage(img7)
                        states_se = 2
                    }
                    2 -> {
                        binding.serverLabelView.visibility = View.VISIBLE
                        closeImage(img7)
                        states_se = 1
                    }
                }
            }
        }
        with(binding.card9) {
            this.setOnClickListener {
                when (states_n) {
                    1 -> {
                        binding.audioLabelView.animateGone()
                        openImage(img9)
                        states_n = 2
                    }
                    2 -> {
                        binding.audioLabelView.animateVisible()
                        closeImage(img9)
                        states_n = 1
                    }
                }
            }
        }
        binding.dailyLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.imageLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.audioLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.systemLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.codeLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.otherLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.serverLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.webLabelView.layoutManager = FlexboxLayoutManager(activity)
        binding.bottomSummary.text = spannable {
            "???".color(colorPrimary())
            "${getDailyData().size + getImageData().size + getAudioData().size + getCodeData().size + getOtherData().size + getServerData().size + getWebData().size + getSystemData().size}".span {
                typeface(Typeface.defaultFromStyle(Typeface.BOLD))
                color(colorPrimary())
                absoluteSize(18, dp = true)
            }
            "?????????".color(colorPrimary())
        }
        binding.dailyLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getDailyData()
        binding.imageLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getImageData()
        binding.audioLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getAudioData()
        binding.systemLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getSystemData()
        binding.codeLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getCodeData()
        binding.otherLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getOtherData()
        binding.serverLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getServerData()
        binding.webLabelView.setup {
            addType<FlexTagModel>(R.layout.item_tv)
            onClick(R.id.chip) {
                getModel<FlexTagModel>().unit.invoke()
            }
        }.models = getWebData()
    }

    private fun getDailyData(): List<FlexTagModel> {
        return listOf(FlexTagModel("?????????") {
            startActivity(Intent(context, RulerActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, NoiseMeasurementActivity::class.java))
        }, FlexTagModel("Google??????") {
            startActivity(Intent(context, TranslateActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, CreatePasswordActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, TimeActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, MetalDetectionActivity::class.java))
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, HistoryTodayActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, MoviesActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, LocationInquireActivity::class.java))
        }, FlexTagModel("QQ??????") {
            startActivity(Intent(context, QQNumQueryActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, MarQueeActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, StepsActivity::class.java))
        }, FlexTagModel("?????????????????????") {
            startActivity(Intent(context, ShortVideoActivity::class.java))
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, ImageParagraphActivity::class.java))
        }, FlexTagModel("Github????????????") {
            startActivity(Intent(context, GithubDownloadActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, PinyinActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, LanzouActivity::class.java))
        }, FlexTagModel("????????????????????????") {
            startActivity(Intent(context, TelephoneActivity::class.java))
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, AudioFormatActivity::class.java))
        }, FlexTagModel("M3U8????????????") {
            startActivity(Intent(context, M3u8Activity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, ZenModeActivity::class.java))
        })
    }

    private fun getImageData(): List<FlexTagModel> {
        return listOf(FlexTagModel("???????????????") {
            startActivity(Intent(context, QRCodeActivity::class.java))
        }, FlexTagModel("LowPoly????????????") {
            startActivity(Intent(context, LowPolyActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, CompressActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, BlackGreyPhotoActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, PhotoTextActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, PicturePixelActivity::class.java))
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, TelephoneActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, ImageToUrlActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, PhotoWaterMarkActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, PhotoMiaoActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, WallpaperCategoryActivity::class.java))
        })
    }

    private fun getSystemData(): List<FlexTagModel> {
        return listOf(FlexTagModel("????????????") {
            startActivity(Intent(context, AppManagerActivity::class.java))
        }, FlexTagModel("?????????????????????") {
            hardwareFloat.loadContext(requireContext())
        }, FlexTagModel("???????????????") {
            hardwareFloat.loadNetworkFloat(requireContext())
        }, FlexTagModel("??????HDR??????") {
            startActivity(Intent(context, HDRCheckActivity::class.java))
        }, FlexTagModel("???????????????") {
            startActivity(Intent(context, MediaScannerActivity::class.java))
        }, FlexTagModel("????????????") {
            val items = arrayOf<CharSequence>("??????",
                "?????????",
                "????????????????????????",
                "????????????",
                "????????????",
                "Recovery",
                "??????9008",
                "FastBoot")
            BottomMenu.show(items).setTitle("????????????").onMenuItemClickListener =
                OnMenuItemClickListener { _: BottomMenu?, _: CharSequence?, index: Int ->
                    when (items[index].toString()) {
                        "??????" -> if (haveRoot()) {
                            runRootCommand("reboot")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "????????????" -> if (haveRoot()) {
                            runRootCommand("su -c setprop persist.sys.safemode 1\nreboot")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "????????????" -> if (haveRoot()) {
                            runRootCommand("reboot -p")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "Recovery" -> if (haveRoot()) {
                            runRootCommand("reboot recovery")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "??????9008" -> if (haveRoot()) {
                            runRootCommand("reboot edl")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "FastBoot" -> if (haveRoot()) {
                            runRootCommand("reboot fastboot")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "?????????" -> if (haveRoot()) {
                            runRootCommand("setprop ctl.restart zygote")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                        "????????????????????????" -> if (haveRoot()) {
                            runRootCommand("pkill -f com.android.systemui")
                        } else {
                            PopTip.show(R.string.no_root)
                        }
                    }
                    false
                }
        }, FlexTagModel("MIUI????????????") {
            startActivity(Intent(context, MIUIShortCutActivity::class.java))
        }, FlexTagModel("????????????") {
            CommonFunctions().setDumpsysBattery(requireActivity())
        }, FlexTagModel("??????????????????") {
            SettingsLoader.deviceInfoDialog(requireContext())
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, MainActivity::class.java))
        }, FlexTagModel("??????????????????") {
            CommonFunctions().saveWallpaper(requireActivity())
        }, FlexTagModel("????????????????????????") {
            startActivity(Intent(context, FontScale::class.java))
        }, FlexTagModel("?????????") {
            CommonFunctions().vibrateFunction(requireActivity())
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, BackupWordLibrary::class.java))
        }, FlexTagModel("Magisk????????????????????????") {
            startActivity(Intent(context, CustomModelActivity::class.java))
        })
    }

    private fun getCodeData(): List<FlexTagModel> {
        return listOf(FlexTagModel("3DES?????????") {
            startActivity(Intent(context, ThDesActivity::class.java))
        }, FlexTagModel("??????64????????????") {

        }, FlexTagModel("AES?????????") {
            startActivity(Intent(context, AesCrypt::class.java))
        }, FlexTagModel("RC4?????????") {
            startActivity(Intent(context, RC4EDActivity::class.java))
        }, FlexTagModel("DES?????????") {
            startActivity(Intent(context, DesCrypt::class.java))
        }, FlexTagModel("Base64?????????") {
            startActivity(Intent(context, BaseConvertActivity::class.java))
        }, FlexTagModel("MD5??????") {
            startActivity(Intent(context, EncryptMD5Activity::class.java))
        }, FlexTagModel("SHA??????") {
            startActivity(Intent(context, SHACrypt::class.java))
        }, FlexTagModel("HmacMD5??????") {
            startActivity(Intent(context, HmacMD5Activity::class.java))
        }, FlexTagModel("HmacSHA??????") {
            startActivity(Intent(context, TelephoneActivity::class.java))
        }, FlexTagModel("??????????????????") {
            startActivity(Intent(context, SpecialTextActivity::class.java))
        })
    }

    private fun getOtherData(): List<FlexTagModel> {
        return listOf(FlexTagModel("QQ????????????") {
            CommonFunctions().openQQFunctions(requireContext())
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, RanDomColorActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, RanDomArticleActivity::class.java))
        }, FlexTagModel("?????????????????????") {
            CommonFunctions().alipayAudio(requireContext(), requireActivity())
        })
    }

    private fun getServerData(): List<FlexTagModel> {
        return listOf(FlexTagModel("Ping") {
            startActivity(Intent(context, PingActivity::class.java))
        }, FlexTagModel("??????ICP????????????") {
            startActivity(Intent(context, BeianActivity::class.java))
        }, FlexTagModel("?????????????????????") {
            startActivity(Intent(context, ServerInfoActivity::class.java))
        }, FlexTagModel("????????????") {
            startActivity(Intent(context, PortsScanActivity::class.java))
        }, FlexTagModel("LAN??????") {
            startActivity(Intent(context, WakeOnLanActivity::class.java))
        })
    }


    private fun getWebData(): List<FlexTagModel> {
        return listOf(FlexTagModel("MiKuTool") {
            startActivity<WebActivity>("url" to "https://tools.miku.ac/")
        }, FlexTagModel("SnapDrop???????????????") {
            startActivity<WebActivity>("url" to "https://snapdrop.fengmuchuan.cn/")
        }, FlexTagModel("????????????????????????") {
            startActivity<WebActivity>("url" to "https://www.remove.bg/zh")
        }, FlexTagModel("PDF????????????") {
            startActivity<WebActivity>("url" to "https://smallpdf.com")
        }, FlexTagModel("??????????????????") {
            startActivity<WebActivity>("url" to "https://convertio.co/zh/audio-converter/")
        }, FlexTagModel("Json??????") {
            startActivity<WebActivity>("url" to "https://www.sojson.com/")
        }, FlexTagModel("???????????????") {
            startActivity<WebActivity>("url" to "https://tool.lu/")
        }, FlexTagModel("Desmos????????????") {
            startActivity<WebActivity>("url" to "https://www.desmos.com/calculator?lang=zh-CN")
        })
    }


    private fun getAudioData(): List<FlexTagModel> {
        return listOf(FlexTagModel("????????????") {

        }, FlexTagModel("????????????") {

        }, FlexTagModel("????????????") {
            startActivity<CompressVideoActivity>()
        }, FlexTagModel("????????????") {
            startActivity<CompressAudioActivity>()
        }, FlexTagModel("??????????????????") {
            startActivity<FormatAudioActivity>()
        }, FlexTagModel("??????????????????") {
            startActivity<FormatVideoActivity>()
        }, FlexTagModel("?????????GIF") {
            startActivity<TOGifActivity>()
        }, FlexTagModel("???????????????") {
            startActivity<TOImageActivity>()
        }, FlexTagModel("????????????") {
            startActivity<MuteVideoActivity>()
        }, FlexTagModel("??????????????????") {

        }, FlexTagModel("???????????????????????????") {
            startActivity<AspectActivity>()
        }, FlexTagModel("?????????????????????????????????") {

        }, FlexTagModel("?????????PCM") {

        }, FlexTagModel("????????????YUV") {
            startActivity<YUVideoActivity>()
        }, FlexTagModel("????????????") {

        }, FlexTagModel("??????????????????") {
            startActivity<CrtActivity>()
        }, FlexTagModel("????????????H264") {
            startActivity<H264Activity>()
        }, FlexTagModel("??????????????????") {
            startActivity<MakeMuteActivity>()
        }, FlexTagModel("????????????ES??????") {

        }, FlexTagModel("?????????????????????") {
            startActivity<ContrastActivity>()
        }, FlexTagModel("MP4??????DVD") {

        }, FlexTagModel("??????????????????") {
            startActivity<BrightActivity>()
        }, FlexTagModel("????????????") {
            startActivity<DenoiseActivity>()
        }, FlexTagModel("???????????????") {

        }, FlexTagModel("??????????????????") {

        }, FlexTagModel("????????????") {
            startActivity<HlsActivity>()
        }, FlexTagModel("M3U8???????????????") {
            startActivity<M3U8Activity>()
        }, FlexTagModel("????????????") {
            startActivity<ReverseActivity>()
        })
    }

}