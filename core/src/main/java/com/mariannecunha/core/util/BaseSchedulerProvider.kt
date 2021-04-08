package com.mariannecunha.core.util

import io.reactivex.Scheduler

data class BaseSchedulerProvider(
    val subscribe: Scheduler,
    val observe: Scheduler
)
