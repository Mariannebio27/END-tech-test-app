package com.mariannecunha.core.util

import io.reactivex.Scheduler

data class SchedulerProvider(
    val subscribe: Scheduler,
    val observe: Scheduler
)
