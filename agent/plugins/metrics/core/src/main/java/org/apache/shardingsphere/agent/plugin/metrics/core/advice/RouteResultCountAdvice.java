/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.agent.plugin.metrics.core.advice;

import org.apache.shardingsphere.agent.api.advice.TargetAdviceObject;
import org.apache.shardingsphere.agent.api.advice.type.InstanceMethodAdvice;
import org.apache.shardingsphere.agent.plugin.metrics.core.MetricsPool;
import org.apache.shardingsphere.agent.plugin.metrics.core.constant.MetricIds;
import org.apache.shardingsphere.infra.route.context.RouteContext;
import org.apache.shardingsphere.infra.route.context.RouteMapper;
import org.apache.shardingsphere.infra.route.context.RouteUnit;

import java.lang.reflect.Method;

/**
 * Route result count advice.
 */
public final class RouteResultCountAdvice implements InstanceMethodAdvice {
    
    static {
        MetricsPool.create(MetricIds.ROUTED_DATA_SOURCES);
        MetricsPool.create(MetricIds.ROUTED_TABLES);
    }
    
    @Override
    public void afterMethod(final TargetAdviceObject target, final Method method, final Object[] args, final Object result) {
        if (null == result) {
            return;
        }
        for (RouteUnit each : ((RouteContext) result).getRouteUnits()) {
            RouteMapper dataSourceMapper = each.getDataSourceMapper();
            MetricsPool.get(MetricIds.ROUTED_DATA_SOURCES).ifPresent(optional -> optional.inc(dataSourceMapper.getActualName()));
            each.getTableMappers().forEach(table -> MetricsPool.get(MetricIds.ROUTED_TABLES).ifPresent(optional -> optional.inc(table.getActualName())));
        }
    }
}
