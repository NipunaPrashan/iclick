package org.iclick.doctor.dbaccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to create database connection
 */
public class DbConnectionManager {
	private static final Log LOG = LogFactory.getLog(DbConnectionManager.class);
	private static volatile DbConnectionManager instance;
	private Connection connection;

	private DbConnectionManager() {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject", "root", "");
			} catch (SQLException e) {
					LOG.error("Error occurred while creating database connection");
			}
	}

	/**
	 * Returns database connector instance, if  instance is null creates an instance
	 * @return dbaccess connector
	 */
	public static DbConnectionManager getInstance() {
		if (instance == null) {
			synchronized (DbConnectionManager.class) {
				if (instance == null) {
					instance = new DbConnectionManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Returns MySQL database connection
	 * @return Database connection
	 */
	public Connection getDbConnection() {
		return connection;
	}

	/**
	 * Closing database connection
	 */
	public void closeDbConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("SQL Exception occurred while Closing connection ", e);
			}
		}
	}
}

