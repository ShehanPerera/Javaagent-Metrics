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

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.wso2.carbon.config.ConfigurationException;

import java.lang.instrument.Instrumentation;


class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("Premain");
        new AgentBuilder.Default()
                .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                .type((ElementMatchers.nameEndsWith("EchoHttpServerHandler")))
                .transform(
                        new AgentBuilder.Transformer.ForAdvice()
                                .include(TimerAdvice.class.getClassLoader())
                                .advice(ElementMatchers.nameContains("channelRead0"), TimerAdvice.class.getName())

                ).installOn(instrumentation);
    }

}

