package com.mariannecunha.presentation

import com.mariannecunha.core.base.MviResult
import com.mariannecunha.domain.model.GlobalProducts

sealed class HomeResult : com.mariannecunha.core.base.MviResult {
    sealed class LoadAllMenswearResult : HomeResult() {
        object Loading : LoadAllMenswearResult()
        data class Success(val products: GlobalProducts) : LoadAllMenswearResult()
        data class Failure(val error: Throwable) : LoadAllMenswearResult()
    }
}
