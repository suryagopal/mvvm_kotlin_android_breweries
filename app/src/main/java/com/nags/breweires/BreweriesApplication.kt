package com.nags.breweires

import android.app.Application
import com.nags.breweires.di.databaseModule
import com.nags.breweires.di.repositoryModule
import com.nags.breweires.di.retrofitModule
import com.nags.breweires.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Main Application class, which is
 * responsible to init the Koin DI.
 */
class BreweriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@BreweriesApplication)
            /*Modules declaration*/
            modules(
                listOf(
                    retrofitModule,
                    repositoryModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }
}