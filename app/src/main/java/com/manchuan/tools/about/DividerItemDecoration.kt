package com.manchuan.tools.about

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.manchuan.tools.about.multitype.MultiTypeAdapter

/**
 * @author drakeet
 */
internal class DividerItemDecoration
/**
 * Creates a divider [RecyclerView.ItemDecoration] that can be used with a
 * [LinearLayoutManager].
 *
 * @param adapter The MultiTypeAdapter
 */(private val adapter: MultiTypeAdapter) : RecyclerView.ItemDecoration() {
    private val dividerClasses = arrayOf(
        License::class.java, Recommendation::class.java
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (adapter.itemCount == 0) {
            outRect[0, 0, 0] = 0
            return
        }
        val items: List<*> = adapter.items
        val position = parent.getChildAdapterPosition(view)
        var should = false
        var i = 0
        while (!should && i < dividerClasses.size) {
            should = (position + 1 < items.size && items[position]!!.javaClass.isAssignableFrom(
                dividerClasses[i]
            )
                    && items[position + 1]!!.javaClass.isAssignableFrom(dividerClasses[i]))
            i++
        }
        if (should) {
            outRect[0, 0, 0] = 1
        } else {
            outRect[0, 0, 0] = 0
        }
    }
}