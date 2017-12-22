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

import org.wso2.carbon.config.ConfigurationException;
import org.wso2.carbon.metrics.core.*;


public class MetricServer {

    private static final MetricServer metricServer = new MetricServer();
    private MetricService metricService;
    private Counter incomeJobs;
    private Timer responsesTime;
    private Meter requestsMeter;
    private Histogram requestsSize;

    private MetricServer() {
        Metrics metrics;
        try {
            metrics = new Metrics(TestUtils.getConfigProvider("metrics.yaml"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        metrics.activate();
        metricService = metrics.getMetricService();
        incomeJobs = metricService.counter(MetricService.name("Netty Server ", "Income Jobs"), Level.INFO);
        requestsMeter = metricService.meter(MetricService.name("Netty Server ", "Request Meter "), Level.INFO);
        requestsSize = metricService.histogram(MetricService.name("Netty Server ", "Request Size"), Level.INFO);
        responsesTime = metricService.timer(MetricService.name("Netty server", "Time to response"), Level.INFO);
    }

    public static MetricServer getInstance() {
        return metricServer;
    }

    public Counter getIncomeJobs() {
        return incomeJobs;
    }

    public Meter getRequestsMeter() {
        return requestsMeter;
    }

    public Histogram getRequestsSize() {
        return requestsSize;
    }

    public Timer getResponsesTime() {
        return responsesTime;
    }
}