# configuring a datasource programmatically

MysqlDataSource dataSource = new MysqlDataSource();
dataSource.setServerName("my_database_host");
dataSource.setUser("my_user");
dataSource.setPassword("my_password");
dataSource.setDatabaseName("my_database_name");
JBDCDataModel dataModel = new MySQLJDBCDataModel(
   dataSource, "my_prefs_table", "my_user_columns",
   "my_item_column", "my_pref_value_column");
   
