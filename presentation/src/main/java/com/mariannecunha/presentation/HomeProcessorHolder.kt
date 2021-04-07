package com.mariannecunha.presentation

import com.mariannecunha.domain.repository.ProductRepository
import com.mariannecunha.domain.util.BaseSchedulerProvider
import io.reactivex.ObservableTransformer
import io.reactivex.Observable

class HomeProcessorHolder (private val productRepository: ProductRepository,
                           private val schedulerProvider: BaseSchedulerProvider) {

    private val loadAllMenswearProcessor =
        ObservableTransformer<HomeAction.LoadAllMenswearAction, HomeResult.LoadAllMenswearResult> { actions ->
            actions.flatMap {
                productRepository.getProducts()
                    .map { creatures -> HomeResult.LoadAllMenswearResult.Success(creatures) }
                    .cast(HomeResult.LoadAllMenswearResult::class.java)
                    .onErrorReturn(HomeResult.LoadAllMenswearResult::Failure)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .startWith(HomeResult.LoadAllMenswearResult.Loading)
            }
        }


//    internal var actionProcessor =
//        ObservableTransformer<HomeAction, HomeResult> { actions ->
//            actions.publish { shared ->
//                Observable.merge(
//                    shared.ofType(HomeAction.LoadAllMenswearAction::class.java).compose(loadAllMenswearProcessor)
//                    .mergeWith(
//                        // Error for not implemented actions
//                        shared.filter { v ->
//                            v !is HomeAction.LoadAllMenswearAction
//                        }.flatMap { w ->
//                            Observable.error<HomeResult.LoadAllMenswearResult>(
//                                IllegalArgumentException("Unknown Action type: $w"))
//                        }
//                    ))
//            }
//        }

}