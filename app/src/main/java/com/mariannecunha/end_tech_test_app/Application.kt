package com.mariannecunha.end_tech_test_app

import android.app.Application
import com.mariannecunha.core.di.coreModule
import com.mariannecunha.data.di.dataModule
import com.mariannecunha.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                listOf(
                    coreModule,
                    dataModule,
                    presentationModule
                )
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
