package com.mariannecunha.core.base

import io.reactivex.Observable

interface MviView<I : com.mariannecunha.core.base.MviIntent, in S : MviViewState> {
    fun intents(): Observable<I>
    fun render(state: S)
}
