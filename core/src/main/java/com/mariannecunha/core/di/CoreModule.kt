package com.mariannecunha.core.di

import com.mariannecunha.core.util.SchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module

val coreModule = module {

    factory {
        getBaseSchedulerProvider()
    }
}

private fun getBaseSchedulerProvider(): SchedulerProvider {
    return SchedulerProvider(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )
}
