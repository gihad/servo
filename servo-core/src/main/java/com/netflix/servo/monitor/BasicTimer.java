/*
 * Copyright (c) 2012. Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.netflix.servo.monitor;

import com.netflix.servo.MonitorContext;

import java.util.concurrent.TimeUnit;

/**
 * User: gorzell
 * Date: 4/10/12
 * Time: 9:03 PM
 * <p/>
 * This implementation is not thread safe.
 */
public class BasicTimer extends AbstractMonitor<Long> implements Timer {
    private final TimeUnit timeUnit;
    private long lastDuration = -1;

    public BasicTimer(MonitorContext context, TimeUnit unit) {
        super(context);
        this.timeUnit = unit;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Record a new value for this timer
     *
     * @param duration
     */
    @Override
    public void record(long duration) {
        this.lastDuration = duration;
    }

    /**
     * Record a new value that was collected with the given TimeUnit
     *
     * @param duration
     * @param timeUnit
     */
    @Override
    public void record(long duration, TimeUnit timeUnit) {
        record(this.timeUnit.convert(duration, timeUnit));
    }

    @Override
    public Long getValue() {
        return Long.valueOf(lastDuration);
    }
}