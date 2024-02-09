/*
 * Copyright (C) 2021. Dylan Cai
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

@file:Suppress("unused")

package com.dylanc.longan.activityresult

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract

fun ActivityResultCaller.registerForLaunchAppSettingsResult(callback: ActivityResultCallback<Unit>) =
  registerForActivityResult(LaunchAppSettingsContract(), callback)

class LaunchAppSettingsContract : ActivityResultContract<Unit, Unit>() {
  override fun createIntent(context: Context, input: Unit): Intent =
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
      .setData(Uri.fromParts("package", context.packageName, null))

  override fun parseResult(resultCode: Int, intent: Intent?) = Unit
}

fun interface AppSettingsScope {
  fun launchAppSettings()
}
