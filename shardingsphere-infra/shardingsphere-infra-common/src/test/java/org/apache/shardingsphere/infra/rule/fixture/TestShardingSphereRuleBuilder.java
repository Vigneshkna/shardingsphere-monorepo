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

package org.apache.shardingsphere.infra.rule.fixture;

import lombok.Getter;
import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.infra.rule.ShardingSphereRule;
import org.apache.shardingsphere.infra.rule.builder.scope.EnhancedSchemaRuleBuilder;
import org.apache.shardingsphere.infra.rule.identifier.scope.SchemaRule;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Map;

public final class TestShardingSphereRuleBuilder implements EnhancedSchemaRuleBuilder<TestRuleConfiguration> {
    
    @Getter
    private static final SchemaRule RULE = new TestShardingSphereRule();
    
    @Override
    public SchemaRule build(final String schemaName, final Map<String, DataSource> dataSourceMap, final DatabaseType databaseType, 
                            final TestRuleConfiguration config, final Collection<ShardingSphereRule> rules) {
        return RULE;
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
    
    @Override
    public Class<TestRuleConfiguration> getTypeClass() {
        return TestRuleConfiguration.class;
    }
}
