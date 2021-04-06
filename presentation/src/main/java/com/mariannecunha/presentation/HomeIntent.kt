package com.mariannecunha.presentation

import com.mariannecunha.domain.MviIntent

sealed class HomeIntent : MviIntent {
    object LoadAllMenswearIntent : HomeIntent()
}
