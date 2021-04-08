package com.mariannecunha.presentation

import com.mariannecunha.core.base.MviIntent

sealed class HomeIntent : com.mariannecunha.core.base.MviIntent {
    object LoadAllMenswearIntent : HomeIntent()
}
