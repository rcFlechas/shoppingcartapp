package com.rcflechas.shoppingcartapp.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.rcflechas.shoppingcartapp.R

object Utilities {

    fun convertLayoutToView(mContext: Context, count: Int, image: Int): Drawable {

        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.item_badge, null)
        (view.findViewById(R.id.icon_badge) as ImageView).setImageResource(image)

        when (count) {
            0 -> {
                val counterTextPanel = view.findViewById<AppCompatTextView>(R.id.count)
                counterTextPanel.visibility = View.GONE
            }
            else -> {
                val textView = view.findViewById(R.id.count) as AppCompatTextView
                textView.text = "$count"
            }
        }

        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)

        view.isDrawingCacheEnabled = true
        view.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return BitmapDrawable(mContext.resources, bitmap)
    }
}