package com.manchuan.tools.genshin.service

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.manchuan.tools.R
import com.manchuan.tools.extensions.loge
import com.manchuan.tools.genshin.appwidget.utils.CardUtils
import com.manchuan.tools.genshin.bean.dailynote.DailyNoteBean

class ExpeditionListViewAdapterService : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory =
        ListViewRemoteViesFactory(applicationContext, p0)

    private class ListViewRemoteViesFactory(val context: Context, val intent: Intent?) :
        RemoteViewsFactory {
        val appWidgetId = intent?.data?.schemeSpecificPart + System.currentTimeMillis()
        private val expeditionList = mutableListOf<DailyNoteBean.ExpeditionsBean>()
        override fun onCreate() {
            CardUtils.context = context
            CardUtils.getCacheDailyNoteBean(CardUtils.mainUser.gameUid)?.expeditions?.forEach {
                expeditionList += it
                loge(it.remained_time)
            }
            loge("已唤醒")
        }

        override fun onDataSetChanged() {
            expeditionList.clear()
            CardUtils.context = context
            CardUtils.getCacheDailyNoteBean(CardUtils.mainUser.gameUid)?.expeditions?.forEach {
                expeditionList += it
            }
        }

        override fun onDestroy() {
            expeditionList.clear()
        }

        override fun getCount(): Int {
            return expeditionList.size
        }

        override fun getViewAt(p0: Int): RemoteViews {
            val bean = expeditionList[p0]
            val remoteVies = RemoteViews(context.packageName, R.layout.item_world_expeditions_small)
            remoteVies.apply {
                loge("time", CardUtils.getRecoverTime(bean.remained_time.toLong()))
                setTextViewText(R.id.time, CardUtils.getRecoverTime(bean.remained_time.toLong()))
                setImageViewResource(
                    R.id.circular_ring,
                    if (bean.remained_time.toLong() == 0L) {
                        R.drawable.ic_expedition_finish
                    } else {
                        R.drawable.ic_expedition_in
                    }
                )
                loadImage(bean.avatar_side_icon, context, R.id.icon, remoteVies)
            }
            return remoteVies
        }

        override fun getLoadingView(): RemoteViews {
            return RemoteViews(context.packageName, R.layout.item_world_expeditions_small)
        }

        override fun getViewTypeCount(): Int {
            return 1
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        private fun loadImage(url: String, context: Context, id: Int, remoteViews: RemoteViews) {
            remoteViews.setImageViewBitmap(
                id,
                Glide.with(context).load(url).submit().get().toBitmap()
            )
            remoteViews.apply {
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(
                        context,
                        ExpeditionListViewAdapterService::class.java
                    ), remoteViews
                )
            }
        }
    }
}

