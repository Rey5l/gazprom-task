package com.reysl.gazprom_task.ui.main

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class StickyHeaderItemDecoration(private val adapter: CityAdapter) : RecyclerView.ItemDecoration() {

    private val headerHeight = 100
    private val sidePadding = 40
    private val headerPaint = Paint().apply {
        color = 0xFFF.toInt()
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = 0xFF000000.toInt()
        textSize = 70f
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        var previousHeader: String? = null
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val header = adapter.cities[position].city.substring(0, 1).uppercase()

            if (header != previousHeader) {
                drawHeader(c, child, header, parent)
                previousHeader = header
            }
        }
    }

    private fun drawHeader(c: Canvas, child: View, header: String, parent: RecyclerView) {
        val left = parent.paddingLeft + sidePadding
        val right = parent.width - parent.paddingRight - sidePadding
        val top = max(parent.paddingTop, child.top - headerHeight)
        val bottom = top + headerHeight

        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), headerPaint)
        c.drawText(header, left.toFloat() + 20, bottom.toFloat() - 20, textPaint)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val header = adapter.cities[position].city.substring(0, 1).uppercase()
        if (position == 0 || header != adapter.cities[position - 1].city.substring(0, 1).uppercase()) {
            outRect.top = headerHeight
        } else {
            outRect.top = 0
        }
    }
}
