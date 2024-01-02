package com.plyonest.interval.domain.interfaces

import com.plyonest.interval.domain.models.IntervalTimer

interface TimerStateInterface {
    fun setState(timer: IntervalTimer)
    fun getState(): IntervalTimer
}