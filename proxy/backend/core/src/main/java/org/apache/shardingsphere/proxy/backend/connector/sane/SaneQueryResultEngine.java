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

package org.apache.shardingsphere.proxy.backend.connector.sane;

import org.apache.shardingsphere.infra.database.core.spi.DatabaseTypedSPILoader;
import org.apache.shardingsphere.infra.database.core.type.DatabaseType;
import org.apache.shardingsphere.infra.executor.sql.execute.result.ExecuteResult;
import org.apache.shardingsphere.infra.executor.sql.execute.result.query.QueryResult;
import org.apache.shardingsphere.infra.executor.sql.execute.result.query.impl.raw.metadata.RawQueryResultColumnMetaData;
import org.apache.shardingsphere.infra.executor.sql.execute.result.query.impl.raw.metadata.RawQueryResultMetaData;
import org.apache.shardingsphere.infra.executor.sql.execute.result.query.impl.raw.type.RawMemoryQueryResult;
import org.apache.shardingsphere.infra.executor.sql.execute.result.query.type.memory.row.MemoryQueryResultDataRow;
import org.apache.shardingsphere.sql.parser.statement.core.statement.SQLStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.SelectStatement;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.Optional;

/**
 * Sane query result engine.
 */
public final class SaneQueryResultEngine {
    
    private final DialectSaneQueryResultEngine dialectEngine;
    
    public SaneQueryResultEngine(final DatabaseType databaseType) {
        dialectEngine = DatabaseTypedSPILoader.findService(DialectSaneQueryResultEngine.class, databaseType).orElse(null);
    }
    
    /**
     * Get sane query result.
     *
     * @param sqlStatement SQL statement
     * @param ex SQL exception
     * @return sane execute result
     */
    public Optional<ExecuteResult> getSaneQueryResult(final SQLStatement sqlStatement, final SQLException ex) {
        if (null == dialectEngine) {
            return sqlStatement instanceof SelectStatement ? Optional.of(getDefaultQueryResult()) : Optional.empty();
        }
        return dialectEngine.getSaneQueryResult(sqlStatement, ex);
    }
    
    private QueryResult getDefaultQueryResult() {
        RawQueryResultColumnMetaData queryResultColumnMetaData = new RawQueryResultColumnMetaData("", "", "", Types.VARCHAR, "VARCHAR", 255, 0);
        MemoryQueryResultDataRow resultDataRow = new MemoryQueryResultDataRow(Collections.singletonList("1"));
        return new RawMemoryQueryResult(new RawQueryResultMetaData(Collections.singletonList(queryResultColumnMetaData)), Collections.singletonList(resultDataRow));
    }
}
