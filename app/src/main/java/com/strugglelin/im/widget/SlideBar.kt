package com.strugglelin.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.strugglelin.im.R
import org.jetbrains.anko.sp

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    var sectionHeight = 0f
    var textBaseline = 0f
    var paint = Paint()
    var onSectionChangeListener: OnSectionChangeListener? = null

    companion object {
        private val SECTIONS = arrayOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        )
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 计算每个字符分配高度
        sectionHeight = h / SECTIONS.size * 1.0f
        val fontMetrics = paint.fontMetrics
        // 计算文本高度
        val textHeiht = fontMetrics.descent - fontMetrics.ascent
        // 计算基准线高度
        textBaseline = sectionHeight / 2 + (textHeiht / 2 - fontMetrics.descent)
        Log.e(
            "tag",
            "sectionHeight=" + sectionHeight + ",descent=" + fontMetrics.descent + ",ascent=" + fontMetrics.ascent
        )
    }

    override fun onDraw(canvas: Canvas) {
        val x = width / 2f
        var baseline = textBaseline
        SECTIONS.forEach {
            canvas.drawText(it, x, baseline, paint)
            baseline += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                // 计算点击字母
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
                Log.e("tag", firstLetter)
                onSectionChangeListener?.onSectionChange(firstLetter)
            }
            MotionEvent.ACTION_MOVE -> {
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
                Log.e("tag", firstLetter)
                onSectionChangeListener?.onSectionChange(firstLetter)
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChangeListener?.onSlideFinish()
            }
            else -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChangeListener?.onSlideFinish()
            }
        }
        return true
    }

    private fun getTouchIndex(event: MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()
        // 越界检查
        if (index <= 0) {
            index = 0
        } else if (index > SECTIONS.size - 1) {
            index = SECTIONS.size - 1
        }
        return index
    }

    interface OnSectionChangeListener {
        fun onSectionChange(firstLetter: String)
        fun onSlideFinish()// 滑动结束
    }
}