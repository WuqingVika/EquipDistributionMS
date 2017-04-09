package com.guanghua.edms.util;

import java.sql.Connection;

import javax.sql.DataSource;
/**
 * 
 * @author wuqingvika
 *
 */
public interface ISQLConnection {
	public Connection createConnection(String conn) throws Exception;
	public DataSource createDataSource(String conn) throws Exception;
}
