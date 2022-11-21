package com.vanced.store

import android.app.Application
import com.vanced.store.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VSApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VSApp)

            modules(
                httpModule,
                databaseModule,
                preferenceModule,

                mainModule,
                browseModule,
                libraryModule,
                reposModule,
                themesModule
            )
        }
    }

}