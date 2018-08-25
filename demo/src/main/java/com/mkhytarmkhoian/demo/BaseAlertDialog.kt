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
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.Window
import com.mkhytarmkhoian.conductor.dialog.DialogController


abstract class BaseAlertDialog(args: Bundle) : DialogController(args) {

    protected lateinit var shadowDrawable: Drawable
    protected lateinit var backgroundPaddings: Rect

    protected var maxWidth: Int = 0
    private val delegate = BaseAlertDialogDelegate()

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        delegate.onContextAvailable(context, this)

        backgroundPaddings = delegate.backgroundPaddings
        shadowDrawable = delegate.shadowDrawable
        maxWidth = delegate.maxWidth
    }

    override fun onCreateDialog(): Dialog {
        val dialog = super.onCreateDialog()
        return delegate.onCreateDialog(dialog)
    }

    override fun setupWindow(view: View?, window: Window?) {
        delegate.setupWindow(view, window)
    }
}