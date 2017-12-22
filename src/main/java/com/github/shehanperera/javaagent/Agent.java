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
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.wso2.carbon.config.ConfigurationException;

import java.lang.instrument.Instrumentation;


class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("Premain");
        new AgentBuilder.Default()
                .type(ElementMatchers.nameContains("EchoHttpServerHandler"))
                .transform((builder, type, classLoader, module) ->
                        builder.method((ElementMatchers.nameContains("channelRead0")))/*This will inspect only 'channelRead0'
                         which gives the output*/
                                .intercept(MethodDelegation.to(Interceptor.class))
                ).installOn(instrumentation);
    }

}

