package com.mariannecunha.presentation

import com.mariannecunha.domain.HomeAction
import com.mariannecunha.domain.HomeResult
import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.util.BaseSchedulerProvider
import io.reactivex.ObservableTransformer

class HomeProcessorHolder(
    private val productRepository: ProductRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    val loadAllMenswearProcessor = ObservableTransformer<HomeAction, HomeResult.LoadAllMenswearResult> { actions ->
        actions.flatMap {
            productRepository.getProducts()
                .map { products ->
                    HomeResult.LoadAllMenswearResult.Success(products)
                }
                .cast(HomeResult.LoadAllMenswearResult::class.java)
                .onErrorReturn(HomeResult.LoadAllMenswearResult::Failure)
                .subscribeOn(schedulerProvider.subscribe)
                .observeOn(schedulerProvider.observe)
                .startWith(HomeResult.LoadAllMenswearResult.Loading)
        }
    }
}
