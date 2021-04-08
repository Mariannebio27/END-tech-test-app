package com.mariannecunha.presentation.di

import com.mariannecunha.core.util.SchedulerProvider
import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.usecase.GetProcessorHolder
import com.mariannecunha.presentation.home.HomeViewModel
import com.mariannecunha.presentation.home.ProductAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        HomeViewModel(
            get<GetProcessorHolder>()
        )
    }

    factory {
        ProductAdapter()
    }

    factory {
        GetProcessorHolder(
            get<ProductRepository>(),
            get<SchedulerProvider>()
        )
    }
}
