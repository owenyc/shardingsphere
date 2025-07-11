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

package org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.CallStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.DMLStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.DeleteStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.DoStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.InsertStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.MergeStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.SelectStatement;
import org.apache.shardingsphere.sql.parser.statement.core.statement.type.dml.UpdateStatement;
import org.apache.shardingsphere.sql.parser.statement.mysql.dcl.MySQLLoadDataStatement;
import org.apache.shardingsphere.sql.parser.statement.mysql.dcl.MySQLLoadXMLStatement;
import org.apache.shardingsphere.sql.parser.statement.postgresql.dml.PostgreSQLCopyStatement;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.SQLCaseAssertContext;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.CallStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.CopyStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.DeleteStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.DoStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.InsertStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.LoadDataStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.LoadXMLStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.MergeStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.SelectStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.asserts.statement.dml.impl.UpdateStatementAssert;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.SQLParserTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.CallStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.CopyStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.DeleteStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.DoStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.InsertStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.LoadDataStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.LoadXMLStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.MergeStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.SelectStatementTestCase;
import org.apache.shardingsphere.test.it.sql.parser.internal.cases.parser.jaxb.statement.dml.UpdateStatementTestCase;

/**
 * DML statement assert.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DMLStatementAssert {
    
    /**
     * Assert DML statement is correct with expected parser result.
     *
     * @param assertContext assert context
     * @param actual actual DML statement
     * @param expected expected parser result
     */
    public static void assertIs(final SQLCaseAssertContext assertContext, final DMLStatement actual, final SQLParserTestCase expected) {
        if (actual instanceof SelectStatement) {
            SelectStatementAssert.assertIs(assertContext, (SelectStatement) actual, (SelectStatementTestCase) expected);
        } else if (actual instanceof UpdateStatement) {
            UpdateStatementAssert.assertIs(assertContext, (UpdateStatement) actual, (UpdateStatementTestCase) expected);
        } else if (actual instanceof MergeStatement) {
            MergeStatementAssert.assertIs(assertContext, (MergeStatement) actual, (MergeStatementTestCase) expected);
        } else if (actual instanceof DeleteStatement) {
            DeleteStatementAssert.assertIs(assertContext, (DeleteStatement) actual, (DeleteStatementTestCase) expected);
        } else if (actual instanceof InsertStatement) {
            InsertStatementAssert.assertIs(assertContext, (InsertStatement) actual, (InsertStatementTestCase) expected);
        } else if (actual instanceof CallStatement) {
            CallStatementAssert.assertIs(assertContext, (CallStatement) actual, (CallStatementTestCase) expected);
        } else if (actual instanceof PostgreSQLCopyStatement) {
            CopyStatementAssert.assertIs(assertContext, (PostgreSQLCopyStatement) actual, (CopyStatementTestCase) expected);
        } else if (actual instanceof DoStatement) {
            DoStatementAssert.assertIs(assertContext, (DoStatement) actual, (DoStatementTestCase) expected);
        } else if (actual instanceof MySQLLoadDataStatement) {
            LoadDataStatementAssert.assertIs(assertContext, (MySQLLoadDataStatement) actual, (LoadDataStatementTestCase) expected);
        } else if (actual instanceof MySQLLoadXMLStatement) {
            LoadXMLStatementAssert.assertIs(assertContext, (MySQLLoadXMLStatement) actual, (LoadXMLStatementTestCase) expected);
        }
    }
}
