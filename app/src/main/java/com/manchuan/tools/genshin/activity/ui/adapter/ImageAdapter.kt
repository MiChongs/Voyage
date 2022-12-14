package com.manchuan.tools.genshin.activity.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.longan.dp
import com.dylanc.longan.roundCorners
import com.manchuan.tools.genshin.ext.loadImage
import com.youth.banner.adapter.BannerAdapter

class ImageAdapter(val list: List<String>) : BannerAdapter<String, ImageAdapter.ImageHolder>(list) {

    override fun onCreateHolder(p0: ViewGroup?, p1: Int): ImageHolder {
        val image = ImageView(p0!!.context)
        image.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.roundCorners = 6.dp
        return ImageHolder(image)
    }

    override fun onBindView(p0: ImageHolder?, p1: String?, p2: Int, p3: Int) {
        loadImage(p0!!.imageView, p1)
    }

    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view as ImageView
    }

}

