package com.mariannecunha.domain.result

import com.mariannecunha.domain.model.Product

sealed class HomeResult : com.mariannecunha.core.base.MviResult {
    sealed class LoadAllMenswearResult : HomeResult() {
        object Loading : LoadAllMenswearResult()
        data class Success(val products: List<Product>) : LoadAllMenswearResult()
        data class Failure(val error: Throwable) : LoadAllMenswearResult()
    }
}
