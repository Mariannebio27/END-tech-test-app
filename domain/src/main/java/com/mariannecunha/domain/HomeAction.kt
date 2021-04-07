package com.mariannecunha.domain

import com.mariannecunha.domain.mvibase.MviAction

sealed class HomeAction : MviAction {
    object LoadAllMenswearAction : HomeAction()
}
