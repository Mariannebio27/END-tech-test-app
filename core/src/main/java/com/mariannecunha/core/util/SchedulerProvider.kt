package com.mariannecunha.core.util

import io.reactivex.rxjava3.core.Scheduler

data class SchedulerProvider(
    val subscribe: Scheduler,
    val observe: Scheduler
)
