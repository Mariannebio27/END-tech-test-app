package com.mariannecunha.end_tech_test_app.app

import android.app.Application
import com.mariannecunha.data.di.dataModule
import com.mariannecunha.end_tech_test_app.BuildConfig
import com.mariannecunha.presentation.di.homeModule
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
                    homeModule,
                    dataModule
                )
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
