package com.mariannecunha.domain.usecase

import com.mariannecunha.core.util.SchedulerProvider
import com.mariannecunha.domain.action.HomeAction
import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.result.HomeResult
import io.reactivex.rxjava3.core.ObservableTransformer

class GetProcessorHolder(
    private val productRepository: ProductRepository,
    private val schedulerProvider: SchedulerProvider
) {

    operator fun invoke() = ObservableTransformer<HomeAction, HomeResult.LoadAllMenswearResult> { actions ->
        actions.flatMap {
            productRepository.getProducts()
                .map { productWrapper ->
                    HomeResult.LoadAllMenswearResult.Success(productWrapper.products.sortedByDescending { it.id })
                }
                .cast(HomeResult.LoadAllMenswearResult::class.java)
                .onErrorReturn(HomeResult.LoadAllMenswearResult::Failure)
                .subscribeOn(schedulerProvider.subscribe)
                .observeOn(schedulerProvider.observe)
                .startWithItem(HomeResult.LoadAllMenswearResult.Loading)
        }
    }
}
