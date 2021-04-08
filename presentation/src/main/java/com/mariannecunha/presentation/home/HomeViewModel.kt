package com.mariannecunha.presentation.home

import androidx.lifecycle.ViewModel
import com.mariannecunha.core.base.MviViewModel
import com.mariannecunha.core.extensions.notOfType
import com.mariannecunha.domain.action.HomeAction
import com.mariannecunha.domain.result.HomeResult
import com.mariannecunha.domain.result.HomeResult.LoadAllMenswearResult
import com.mariannecunha.domain.usecase.GetProcessorHolder
import com.mariannecunha.presentation.home.HomeIntent.LoadAllMenswearIntent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class HomeViewModel(private val getProcessorHolder: GetProcessorHolder) :
    ViewModel(),
    MviViewModel<HomeIntent, HomeViewState> {

    private val intentsSubject: PublishSubject<HomeIntent> = PublishSubject.create()
    private val statesObservable: Observable<HomeViewState> = compose()

    override fun processIntents(intents: Observable<HomeIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<HomeViewState> = statesObservable

    private fun compose(): Observable<HomeViewState> {
        return intentsSubject
            .compose(getIntentFilter())
            .map(this::actionFromIntent)
            .compose(getProcessorHolder())
            .scan(
                HomeViewState.idle(),
                reducer
            )
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun getIntentFilter() = ObservableTransformer<HomeIntent, HomeIntent> { intents ->
        intents.publish { shared ->
            Observable.merge(
                shared.ofType(LoadAllMenswearIntent::class.java).take(1),
                shared.notOfType(LoadAllMenswearIntent::class.java)
            )
        }
    }

    private fun actionFromIntent(intent: HomeIntent): HomeAction {
        return when (intent) {
            is LoadAllMenswearIntent -> HomeAction.LoadAllMenswearAction
        }
    }

    companion object {
        private val reducer =
            BiFunction { previousState: HomeViewState, result: HomeResult ->
                when (result) {
                    is LoadAllMenswearResult -> when (result) {
                        is LoadAllMenswearResult.Success -> {
                            previousState.copy(isLoading = false, products = result.products)
                        }
                        is LoadAllMenswearResult.Failure -> previousState.copy(
                            isLoading = false,
                            error = result.error
                        )
                        is LoadAllMenswearResult.Loading -> previousState.copy(
                            isLoading = true
                        )
                    }
                }
            }
    }
}
