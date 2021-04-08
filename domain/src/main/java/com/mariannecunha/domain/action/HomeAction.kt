package com.mariannecunha.domain.action

sealed class HomeAction : com.mariannecunha.core.base.MviAction {
    object LoadAllMenswearAction : HomeAction()
}
