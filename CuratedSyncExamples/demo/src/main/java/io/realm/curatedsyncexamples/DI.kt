/*
 * Copyright 2023 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.realm.curatedsyncexamples

import io.realm.curatedsyncexamples.ui.ExamplesScreenViewModel
import io.realm.kotlin.mongodb.App
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Enum with all the required App Services App.
 */
enum class Apps(val appId: String) {
    FIELD_ENCRYPTION_APP(FIELD_ENCRYPTION_APP_ID);

    val qualifier = named(appId)
}

val appsModule = module {
    for (app in Apps.values()) {
        single(app.qualifier) { App.create(app.appId) }
    }

    viewModel {
        val apps = Apps.values()
            .map { appEntry ->
                get<App>(appEntry.qualifier)
            }

        ExamplesScreenViewModel(apps)
    }
}
