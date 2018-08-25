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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler
import com.mkhytarmkhoian.demo.progress.ProgressDialogController

class HomeController : Controller() {

    private var progressDialog: ProgressDialogController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)

        view.findViewById<Button>(R.id.classic).setOnClickListener { onClassicClicked() }
        view.findViewById<Button>(R.id.progress).setOnClickListener { onProgressClicked() }

        return view
    }

    private fun onClassicClicked() {
        router.pushController(RouterTransaction.with(AlertDialogController.newInstance())
                .pushChangeHandler(SimpleSwapChangeHandler(false))
                .popChangeHandler(SimpleSwapChangeHandler()))

    }

    private fun onProgressClicked() {
        progressDialog = ProgressDialogController.showDialog(
                router,
                R.string.all_loading, true
        )
    }
}
