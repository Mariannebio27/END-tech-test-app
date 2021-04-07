package com.mariannecunha.presentation

import com.mariannecunha.domain.mvibase.MviAction

sealed class HomeAction : MviAction {
    object LoadAllMenswearAction : HomeAction()
    object ClearAllMenswearAction : HomeAction()
}
