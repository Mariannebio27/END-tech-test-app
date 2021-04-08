package com.mariannecunha.core.base

import io.reactivex.Observable

interface MviViewModel<I : com.mariannecunha.core.base.MviIntent, S : MviViewState> {
    fun processIntents(intents: Observable<I>)
    fun states(): Observable<S>
}
