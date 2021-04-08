package com.mariannecunha.presentation.home

sealed class HomeIntent : com.mariannecunha.core.base.MviIntent {
    object LoadAllMenswearIntent : HomeIntent()
}
