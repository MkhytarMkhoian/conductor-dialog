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
import android.os.Bundle
import android.support.v7.app.AlertDialog

class AlertDialogController(args: Bundle) : BaseAlertDialog(args) {

    companion object {
        fun newInstance(): AlertDialogController {
            val args = Bundle()
            return AlertDialogController(args)
        }
    }

    override fun onCreateDialog(): Dialog {
        return AlertDialog.Builder(activity!!)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("Ok", null)
                .create()
    }
}
