package com.mariannecunha.presentation

import com.mariannecunha.core.base.MviViewState
import com.mariannecunha.domain.model.GlobalProducts
import com.mariannecunha.domain.model.Product

data class HomeViewState(
    val isLoading: Boolean,
    val products: GlobalProducts,
    val error: Throwable?
) : MviViewState {
    companion object {
        fun idle(): HomeViewState = HomeViewState(
            isLoading = false,
            products = GlobalProducts(products = mutableListOf<Product>()),
            error = null
        )
    }
}
