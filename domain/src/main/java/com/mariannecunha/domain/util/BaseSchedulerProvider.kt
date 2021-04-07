package com.mariannecunha.domain.util

import io.reactivex.Scheduler

data class BaseSchedulerProvider(
    val subscribe: Scheduler,
    val observe: Scheduler
)
