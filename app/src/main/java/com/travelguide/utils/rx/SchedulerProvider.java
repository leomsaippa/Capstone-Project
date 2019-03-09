package com.travelguide.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
