/*
 * Copyright 2020 Lalafo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mkhytarmkhoian.demo.progress

import android.content.Context
import android.graphics.Canvas
import android.text.Layout
import android.text.TextUtils
import android.util.AttributeSet
import android.view.ViewGroup
import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder
import com.mkhytarmkhoian.demo.R
import com.mkhytarmkhoian.demo.dpToPx
import com.mkhytarmkhoian.demo.getColorCompat
import com.mkhytarmkhoian.demo.spToPx

class ProgressDialogView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ViewGroup(context, attrs, defStyleAttr) {

    private val messageTextLayoutBuilder: TextLayoutBuilder
    private var messageLayout: Layout? = null
    private var message: String? = null
    private var messageLeft: Float = 0f
    private var messageTop: Float = 0f

    private var radialProgressView: RadialProgressView = RadialProgressView(context)

    private val dimen44dp: Int = context.dpToPx(44f)
    private val dimen24dp: Int = context.dpToPx(24f)

    private var inLayout: Boolean = false

    init {
        setWillNotDraw(false)

        addView(radialProgressView, LayoutParams(dimen44dp, dimen44dp))

        messageTextLayoutBuilder = TextLayoutBuilder()
                .setTextColor(context.getColorCompat(R.color.colorPrimary))
                .setTextSize(context.spToPx(16f).toInt())
                .setSingleLine(true)
                .setEllipsize(TextUtils.TruncateAt.END)
    }

    fun setMessage(resId: Int) {
        setMessage(resources.getString(resId))
    }

    fun setMessage(message: String?) {
        if (this.message.equals(message)) {
            return
        }
        messageLayout = null
        this.message = message

        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        inLayout = true

        val width = MeasureSpec.getSize(widthMeasureSpec)

        radialProgressView.measure(
                MeasureSpec.makeMeasureSpec(radialProgressView.layoutParams.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(radialProgressView.layoutParams.height, MeasureSpec.EXACTLY)
        )

        val height = radialProgressView.measuredHeight + dimen24dp * 2 + paddingTop + paddingBottom
        val messageMaxWidth = width - radialProgressView.measuredWidth - dimen24dp * 2 - dimen24dp

        if (messageLayout == null || messageLayout!!.text != message) {
            messageLayout = messageTextLayoutBuilder
                    .setText(message)
                    .setWidth(messageMaxWidth)
                    .build()
        }

        setMeasuredDimension(width, height)
        inLayout = false
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val parentLeft = paddingLeft + dimen24dp
        val parentTop = paddingTop + dimen24dp
        val parentBottom = bottom - top - paddingBottom - dimen24dp

        radialProgressView.layout(
                parentLeft,
                parentTop,
                parentLeft + radialProgressView.measuredWidth,
                parentTop + radialProgressView.measuredHeight
        )

        messageLayout?.let {
            messageLeft = parentLeft + radialProgressView.measuredWidth + dimen24dp.toFloat()
            messageTop = parentTop + (parentBottom - parentTop - it.height) / 2f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        messageLayout?.let {
            canvas.save()
            canvas.translate(messageLeft, messageTop)
            it.draw(canvas)
            canvas.restore()
        }
    }

    override fun requestLayout() {
        if (inLayout) {
            return
        }
        super.requestLayout()
    }

    override fun hasOverlappingRendering(): Boolean = false
}