package com.plyonest.interval.domain.models

data class Interval(
    val name: String,
    val durationInMillis: Long
)

data class IntervalTimer(
    val name: String,
    val intervals: List<Interval>
)