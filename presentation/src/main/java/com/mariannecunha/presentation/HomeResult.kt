package com.mariannecunha.presentation

import com.mariannecunha.domain.MviResult
import com.mariannecunha.domain.model.Product

sealed class HomeResult : MviResult {
    sealed class LoadAllMenswearResult : HomeResult() {
        object Loading : LoadAllMenswearResult()
        data class Success(val product: List<Product>) : LoadAllMenswearResult()
        data class Failure(val error: Throwable) : LoadAllMenswearResult()
    }
}
