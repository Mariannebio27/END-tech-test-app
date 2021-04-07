package com.mariannecunha.presentation

import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.util.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class HomeProcessorHolder(
    private val productRepository: ProductRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val loadAllMenswearProcessor =
        ObservableTransformer<HomeAction.LoadAllMenswearAction, HomeResult.LoadAllMenswearResult> { actions ->
            actions.flatMap {
                productRepository.getProducts()
                    .map { products -> HomeResult.LoadAllMenswearResult.Success(products) }
                    .cast(HomeResult.LoadAllMenswearResult::class.java)
                    .onErrorReturn(HomeResult.LoadAllMenswearResult::Failure)
                    .subscribeOn(schedulerProvider.subscribe)
                    .observeOn(schedulerProvider.observe)
                    .startWith(HomeResult.LoadAllMenswearResult.Loading)
            }
        }

    private val clearAllMenswearProcessor =
        ObservableTransformer<HomeAction.ClearAllMenswearAction, HomeResult.ClearAllMenswearResult> { actions ->
            actions.flatMap {
                productRepository.getProducts()
                    .map { products -> HomeResult.ClearAllMenswearResult.Success(products) }
                    .cast(HomeResult.ClearAllMenswearResult::class.java)
                    .onErrorReturn(HomeResult.ClearAllMenswearResult::Failure)
                    .subscribeOn(schedulerProvider.subscribe)
                    .observeOn(schedulerProvider.observe)
                    .startWith(HomeResult.ClearAllMenswearResult.Loading)
            }
        }

    internal var actionProcessor =
        ObservableTransformer<HomeAction, HomeResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(HomeAction.LoadAllMenswearAction::class.java).compose(loadAllMenswearProcessor),
                    shared.ofType(HomeAction.ClearAllMenswearAction::class.java).compose(clearAllMenswearProcessor)
                )
                    .mergeWith(
                        // Error for not implemented actions
                        shared.filter { v ->
                            v !is HomeAction.LoadAllMenswearAction &&
                                v !is HomeAction.ClearAllMenswearAction
                        }.flatMap { w ->
                            Observable.error<HomeResult>(
                                IllegalArgumentException("Unknown Action type: $w")
                            )
                        }
                    )
            }
        }
}
