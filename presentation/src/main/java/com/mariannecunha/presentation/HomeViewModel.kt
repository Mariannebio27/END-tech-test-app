package com.mariannecunha.presentation

import androidx.lifecycle.ViewModel
import com.mariannecunha.domain.HomeAction
import com.mariannecunha.domain.HomeResult
import com.mariannecunha.domain.HomeResult.LoadAllMenswearResult
import com.mariannecunha.domain.mvibase.MviViewModel
import com.mariannecunha.domain.util.notOfType
import com.mariannecunha.presentation.HomeIntent.LoadAllMenswearIntent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class HomeViewModel(
    private val actionProcessorHolder: HomeProcessorHolder
) : ViewModel(), MviViewModel<HomeIntent, HomeViewState> {

    private val intentsSubject: PublishSubject<HomeIntent> = PublishSubject.create()
    private val statesObservable: Observable<HomeViewState> = compose()

    private val intentFilter: ObservableTransformer<HomeIntent, HomeIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadAllMenswearIntent::class.java).take(1),
                    shared.notOfType(LoadAllMenswearIntent::class.java)
                )
            }
        }

    override fun processIntents(intents: Observable<HomeIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<HomeViewState> = statesObservable

    private fun compose(): Observable<HomeViewState> {
        return intentsSubject
            .compose(intentFilter)
            .map(this::actionFromIntent)
            .compose(actionProcessorHolder.loadAllMenswearProcessor)
            .scan(HomeViewState.idle(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
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
