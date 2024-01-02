package com.plyonest.interval.data

import com.plyonest.interval.domain.interfaces.TimerStateInterface
import com.plyonest.interval.domain.models.IntervalTimer

class TimerState: TimerStateInterface {
    private lateinit var intervalTimer: IntervalTimer

    override fun setState(timer: IntervalTimer) {
        intervalTimer = timer
    }
    override fun getState(): IntervalTimer {
        return intervalTimer
    }
}
