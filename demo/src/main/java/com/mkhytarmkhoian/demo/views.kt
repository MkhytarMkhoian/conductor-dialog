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

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup

@Suppress("DEPRECATION")
fun View.setBackgroundCompat(drawable: Drawable?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        background = drawable
    } else {
        setBackgroundDrawable(drawable)
    }
}

fun View.canTextInput(): Boolean {
    var v = this
    if (v.onCheckIsTextEditor()) {
        return true
    }

    if (v !is ViewGroup) {
        return false
    }

    val vg = v
    var i = vg.childCount
    while (i > 0) {
        i--
        v = vg.getChildAt(i)
        if (v.canTextInput()) {
            return true
        }
    }

    return false
}