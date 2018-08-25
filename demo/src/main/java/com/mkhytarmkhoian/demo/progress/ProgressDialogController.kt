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

package com.mkhytarmkhoian.demo.progress

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.mkhytarmkhoian.demo.BaseAlertDialog
import com.mkhytarmkhoian.demo.setBackgroundCompat

class ProgressDialogController(args: Bundle) : BaseAlertDialog(args) {

    private lateinit var layout: ProgressDialogView

    companion object {
        val TAG: String = ProgressDialogController::class.java.name
        private const val MESSAGE = "message"
        private const val IS_CANCELABLE = "is_cancelable"

        fun newInstance(message: Int, isCancelable: Boolean): ProgressDialogController {
            val args = Bundle()
            args.putInt(MESSAGE, message)
            args.putBoolean(IS_CANCELABLE, isCancelable)
            return ProgressDialogController(args)
        }

        fun showDialog(router: Router, message: Int, isCancelable: Boolean): ProgressDialogController {
            val dialog = newInstance(message, isCancelable)
            dialog.show(router, TAG)
            return dialog
        }
    }

    override fun inflateDialogView(inflater: LayoutInflater, savedViewState: Bundle?): View? {
        layout = ProgressDialogView(inflater.context)
        layout.fitsSystemWindows = Build.VERSION.SDK_INT >= 21
        layout.setBackgroundCompat(shadowDrawable)
        layout.setPadding(backgroundPaddings.left, backgroundPaddings.top, backgroundPaddings.right, backgroundPaddings.bottom)
        layout.layoutParams = ViewGroup.MarginLayoutParams(maxWidth, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
        return layout
    }

    override fun onBindDialogView(view: View, savedViewState: Bundle?) {
        isCancelable = args.getBoolean(IS_CANCELABLE)
        dialog?.setCancelable(isCancelable)

        layout.setMessage(args.getInt(MESSAGE))
    }
}
