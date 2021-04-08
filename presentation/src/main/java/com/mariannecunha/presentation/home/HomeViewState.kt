package com.mariannecunha.presentation.home

import com.mariannecunha.core.base.MviViewState
import com.mariannecunha.domain.model.Product

data class HomeViewState(
    val isLoading: Boolean,
    val products: List<Product>,
    val error: Throwable?
) : MviViewState {
    companion object {
        fun idle(): HomeViewState =
            HomeViewState(
                isLoading = false,
                products = emptyList(),
                error = null
            )
    }
}
