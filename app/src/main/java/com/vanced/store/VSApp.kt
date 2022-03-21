package com.vanced.store

import android.app.Application
import com.vanced.store.di.httpModule
import com.vanced.store.di.managerModule
import com.vanced.store.di.repositoryModule
import com.vanced.store.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VSApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VSApp)

            modules(
                httpModule,
                repositoryModule,
                viewModelModule,
                managerModule
            )
        }
    }

}