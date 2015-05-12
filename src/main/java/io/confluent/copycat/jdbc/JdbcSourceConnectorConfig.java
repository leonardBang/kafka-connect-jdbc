/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.confluent.copycat.jdbc;

import java.util.Properties;

import io.confluent.common.config.AbstractConfig;
import io.confluent.common.config.ConfigDef;
import io.confluent.common.config.ConfigDef.Importance;
import io.confluent.common.config.ConfigDef.Type;

public class JdbcSourceConnectorConfig extends AbstractConfig {

  public static final String CONNECTION_URL_CONFIG = "connection.url";
  private static final String CONNECTION_URL_DOC = "JDBC connection URL for the database to load.";

  public static final String POLL_INTERVAL_MS_CONFIG = "poll.interval.ms";
  private static final String POLL_INTERVAL_MS_DOC = "";
  public static final int POLL_INTERVAL_MS_DEFAULT = 5000;

  public static final String BATCH_MAX_ROWS_CONFIG = "batch.max.rows";
  private static final String BATCH_MAX_ROWS_DOC =
      "Maximum number of rows to include in a single batch when polling for new data. This "
      + "setting can be used to limit the amount of data buffered internally in the connector.";
  public static final int BATCH_MAX_ROWS_DEFAULT = 100;

  public static final String AUTOINCREMENT_CONFIG = "autoincrement";
  private static final String AUTOINCREMENT_DOC =
      "If true, use autoincrement columns to perform incremental loading of data by limiting each"
      + " query to newer IDs than the last one seen in the previous query. Note that in this mode"
      + " it is not possible to detect modifications to or deletions of existing rows. This mode "
      + "also requires sorting each queries results to ensure at least once delivery.";
  public static final boolean AUTOINCREMENT_DEFAULT = false;

  static ConfigDef config = new ConfigDef()
      .define(CONNECTION_URL_CONFIG, Type.STRING, Importance.HIGH, CONNECTION_URL_DOC)
      .define(POLL_INTERVAL_MS_CONFIG, Type.INT, POLL_INTERVAL_MS_DEFAULT, Importance.HIGH,
              POLL_INTERVAL_MS_DOC)
      .define(BATCH_MAX_ROWS_CONFIG, Type.INT, BATCH_MAX_ROWS_DEFAULT, Importance.LOW,
              BATCH_MAX_ROWS_DOC)
      .define(AUTOINCREMENT_CONFIG, Type.BOOLEAN, AUTOINCREMENT_DEFAULT, Importance.HIGH,
              AUTOINCREMENT_DOC);

  JdbcSourceConnectorConfig(Properties props) {
    super(config, props);
  }
}
