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
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator

import com.mkhytarmkhoian.demo.R
import com.mkhytarmkhoian.demo.dpToPx
import com.mkhytarmkhoian.demo.getColorCompat

class RadialProgressView(context: Context) : View(context) {

    private var lastUpdateTime: Long = 0
    private var radOffset: Float = 0.toFloat()
    private var currentCircleLength: Float = 0.toFloat()
    private var risingCircleLength: Boolean = false
    private var currentProgressTime: Float = 0.toFloat()
    private val cicleRect = RectF()

    private var progressColor: Int = 0

    private val decelerateInterpolator: DecelerateInterpolator
    private val accelerateInterpolator: AccelerateInterpolator
    private val progressPaint: Paint
    private val rotationTime = 2000f
    private val risingTime = 500f
    private var size: Int = 0

    init {

        size = context.dpToPx(40f)

        progressColor = context.getColorCompat(R.color.colorAccent)
        decelerateInterpolator = DecelerateInterpolator()
        accelerateInterpolator = AccelerateInterpolator()
        progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeCap = Paint.Cap.ROUND
        progressPaint.strokeWidth = context.dpToPx(3f).toFloat()
        progressPaint.color = progressColor
    }

    private fun updateAnimation() {
        val newTime = System.currentTimeMillis()
        var dt = newTime - lastUpdateTime
        if (dt > 17) {
            dt = 17
        }
        lastUpdateTime = newTime

        radOffset += 360 * dt / rotationTime
        val count = (radOffset / 360).toInt()
        radOffset -= (count * 360).toFloat()

        currentProgressTime += dt.toFloat()
        if (currentProgressTime >= risingTime) {
            currentProgressTime = risingTime
        }
        if (risingCircleLength) {
            currentCircleLength = 4 + 266 * accelerateInterpolator.getInterpolation(currentProgressTime / risingTime)
        } else {
            currentCircleLength = 4 - 270 * (1.0f - decelerateInterpolator.getInterpolation(currentProgressTime / risingTime))
        }
        if (currentProgressTime == risingTime) {
            if (risingCircleLength) {
                radOffset += 270f
                currentCircleLength = -266f
            }
            risingCircleLength = !risingCircleLength
            currentProgressTime = 0f
        }
        invalidate()
    }

    fun setSize(value: Int) {
        size = value
        invalidate()
    }

    fun setProgressColor(color: Int) {
        progressColor = color
        progressPaint.color = progressColor
    }

    override fun onDraw(canvas: Canvas) {
        val x = (measuredWidth - size) / 2
        val y = (measuredHeight - size) / 2
        cicleRect.set(x.toFloat(), y.toFloat(), (x + size).toFloat(), (y + size).toFloat())
        canvas.drawArc(cicleRect, radOffset, currentCircleLength, false, progressPaint)
        updateAnimation()
    }
}
