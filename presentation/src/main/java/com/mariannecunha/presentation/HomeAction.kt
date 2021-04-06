package com.mariannecunha.presentation

import com.mariannecunha.domain.MviAction

sealed class HomeAction : MviAction {
    object LoadAllCreaturesAction : HomeAction()
}
