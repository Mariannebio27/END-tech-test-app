package com.mariannecunha.domain.usecase

import com.mariannecunha.core.util.SchedulerProvider
import com.mariannecunha.domain.action.HomeAction
import com.mariannecunha.domain.result.HomeResult
import com.mariannecunha.domain.repository.ProductRepository
import io.reactivex.ObservableTransformer

class GetProcessorHolder(
    private val productRepository: ProductRepository,
    private val schedulerProvider: SchedulerProvider
) {

    operator fun invoke()  = ObservableTransformer<HomeAction, HomeResult.LoadAllMenswearResult> { actions ->
        actions.flatMap {
            productRepository.getProducts()
                .map { productWrapper ->
                    HomeResult.LoadAllMenswearResult.Success(emptyList())
                }
                .cast(HomeResult.LoadAllMenswearResult::class.java)
                .onErrorReturn(HomeResult.LoadAllMenswearResult::Failure)
                .subscribeOn(schedulerProvider.subscribe)
                .observeOn(schedulerProvider.observe)
                .startWith(HomeResult.LoadAllMenswearResult.Loading)
        }
    }
}
