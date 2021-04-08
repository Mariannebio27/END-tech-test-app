package com.mariannecunha.presentation

import com.mariannecunha.core.base.MviAction

sealed class HomeAction : com.mariannecunha.core.base.MviAction {
    object LoadAllMenswearAction : HomeAction()
}
