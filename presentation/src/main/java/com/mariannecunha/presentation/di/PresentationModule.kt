package com.mariannecunha.presentation.di

import com.mariannecunha.core.util.BaseSchedulerProvider
import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.presentation.HomeProcessorHolder
import com.mariannecunha.presentation.HomeViewModel
import com.mariannecunha.presentation.MenswearAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

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
}
