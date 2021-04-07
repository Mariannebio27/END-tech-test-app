package com.mariannecunha.presentation.di

import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.util.BaseSchedulerProvider
import com.mariannecunha.presentation.HomeProcessorHolder
import com.mariannecunha.presentation.HomeViewModel
import com.mariannecunha.presentation.MenswearAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel(
            get<HomeProcessorHolder>()
        )
    }

    factory {
        MenswearAdapter()
    }

    factory {
        HomeProcessorHolder(
            get<ProductRepository>(),
            get<BaseSchedulerProvider>()
        )
    }

    factory {
        getBaseSchedulerProvider()
    }
}

private fun getBaseSchedulerProvider(): BaseSchedulerProvider {
    return BaseSchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())
}
