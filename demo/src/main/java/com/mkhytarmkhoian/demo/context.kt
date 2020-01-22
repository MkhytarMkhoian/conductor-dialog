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

package com.mkhytarmkhoian.demo

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.appcompat.content.res.AppCompatResources
import android.view.WindowManager

fun Context.getDrawableCompat(resId: Int): Drawable {
    return AppCompatResources.getDrawable(this, resId)!!
}

fun Context.getColorCompat(id: Int): Int {
    return ContextCompat.getColor(this, id)
}


fun Context.dpToPx(dp: Float): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.spToPx(sp: Float): Float {
    val scale = resources.displayMetrics.scaledDensity
    return sp * scale
}

fun Context.getWindowDimensions(): Point {
    val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun Context.getWindowWidth(): Int {
    return getWindowDimensions().x
}

fun Context.getWindowHeight(): Int {
    return getWindowDimensions().y
}