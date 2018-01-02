/*
 * Copyright 2017 Shehan Perera
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shehanperera.javaagent;

import io.netty.handler.codec.http.FullHttpRequest;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.wso2.carbon.metrics.core.Timer;


class TimerAdvice {

    @Advice.OnMethodEnter
    static Timer.Context enter() {
        MetricServer metricServer = MetricServer.getInstance();
        metricServer.getRequestsMeter().mark();
        metricServer.getIncomeJobs().inc();
        Timer.Context context;
        context = metricServer.getResponsesTime().start();
        return context;
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Enter Timer.Context context, @Argument(1) FullHttpRequest msg) {
        MetricServer metricServer = MetricServer.getInstance();
        metricServer.getRequestsSize().update(msg.content().readableBytes());
        context.stop();
    }
/* @Argument(1) FullHttpRequest msg will give the size of request goes to channelRead0 method */
}