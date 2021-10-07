package com.artelsv.petprojectsecond.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: 07.10.2021 перенеси в ui весь этот package (customview), в data должно лежать только то, что связано с данными, ну или если не надо то удали)
open class SmartNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    override fun measureChildWithMargins(child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int) {
        if (findNestedRecyclerView(child) != null) {
            val lp = child.layoutParams as MarginLayoutParams
            val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                lp.topMargin + lp.bottomMargin, MeasureSpec.AT_MOST
            )
            child.measure(parentWidthMeasureSpec, childHeightMeasureSpec)
        } else {
            super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
        }
    }

    private fun findNestedRecyclerView(view: View): RecyclerView? {
        if (view is RecyclerView) {
            val vertical = (view.layoutManager as? LinearLayoutManager)?.orientation == LinearLayoutManager.VERTICAL
            if (vertical) return view
        }

        if (view is ViewGroup) {
            view.forEach { child ->
                val rv = findNestedRecyclerView(child)
                if (rv != null) return rv
            }
        }

        return null
    }
}