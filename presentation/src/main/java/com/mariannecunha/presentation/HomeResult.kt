package com.mariannecunha.presentation

import com.mariannecunha.domain.model.GlobalProducts
import com.mariannecunha.domain.mvibase.MviResult

sealed class HomeResult : MviResult {
    sealed class LoadAllMenswearResult : HomeResult() {
        object Loading : LoadAllMenswearResult()
        data class Success(val products: GlobalProducts) : LoadAllMenswearResult()
        data class Failure(val error: Throwable) : LoadAllMenswearResult()
    }

    sealed class ClearAllMenswearResult : HomeResult() {
        object Loading : ClearAllMenswearResult()
        data class Success(val products: GlobalProducts) : ClearAllMenswearResult()
        data class Failure(val error: Throwable) : ClearAllMenswearResult()
    }
}
