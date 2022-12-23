package com.isaac.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			String Jdbculr = "jdbc:oracle:thin:@kanto:1521:orcl";
			String user = "AGENDA";
			String password = "123456";
			return DriverManager.getConnection(Jdbculr, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
