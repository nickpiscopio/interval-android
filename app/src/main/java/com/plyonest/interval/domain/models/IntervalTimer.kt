package com.plyonest.interval.domain.models

data class IntervalTimer(
    val name: String,
    val rounds: Int,
    val highIntervalInMillis: Long,
    val lowIntervalInMillis: Long
)