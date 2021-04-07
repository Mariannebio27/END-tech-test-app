package com.mariannecunha.presentation

import com.mariannecunha.domain.mvibase.MviIntent

sealed class HomeIntent : MviIntent {
    object LoadAllMenswearIntent : HomeIntent()
}
