/*
 * Copyright 2018 Mkhytar Mkhoian, Inc.
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

import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.mkhytarmkhoian.conductor.dialog.DialogController
import com.mkhytarmkhoian.conductor.dialog.DialogController.STYLE_NORMAL

class BaseAlertDialogDelegate {

    lateinit var shadowDrawable: Drawable
    lateinit var backgroundPaddings: Rect

    var maxWidth: Int = 0

    fun onContextAvailable(context: Context, controller: DialogController) {
        controller.setStyle(STYLE_NORMAL, R.style.Lalafo_TransparentDialog)

        backgroundPaddings = Rect()
        shadowDrawable = context.getDrawableCompat(R.drawable.popup_fixed_alert).mutate()
        shadowDrawable.colorFilter = PorterDuffColorFilter(context.getColorCompat(R.color.white), PorterDuff.Mode.MULTIPLY)
        shadowDrawable.getPadding(backgroundPaddings)

        val calculatedWidth = context.getWindowWidth() - context.dpToPx(48f)
        val width = if (App.isTablet) {
            if (App.isSmallTablet) {
                context.dpToPx(446f)
            } else {
                context.dpToPx(496f)
            }
        } else {
            context.dpToPx(360f)
        }

        maxWidth = Math.min(width, calculatedWidth) + backgroundPaddings.left + backgroundPaddings.right
    }

    fun onCreateDialog(dialog: Dialog): Dialog {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun setupWindow(view: View?, windowSrc: Window?) {
        windowSrc?.let { window ->
            val params = WindowManager.LayoutParams()
            params.copyFrom(window.attributes)
            params.windowAnimations = R.style.AlertDialogAnimation
            params.dimAmount = 0.6f
            params.width = maxWidth
            params.flags = params.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND

            if (view == null || !view.canTextInput()) {
                params.flags = params.flags or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
            }
            window.attributes = params
        }
    }
}