package com.mariannecunha.core.di

import com.mariannecunha.core.util.BaseSchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val coreModule = module {

    factory {
        getBaseSchedulerProvider()
    }
}

private fun getBaseSchedulerProvider(): BaseSchedulerProvider {
    return BaseSchedulerProvider(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )
}
