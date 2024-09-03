package util;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DBConnect {
	private Connection connect;
	private String jdbcUrl = "jdbc:postgresql://localhost:5432/crypto_price_tracker";
	private String userName = "postgres";
	private String password = "123";

	public Connection getConnect() {
		if (this.connect == null) {
			try {
				Class.forName("org.postgresql.Driver");
				this.connect = DriverManager.getConnection(jdbcUrl, userName, password);
			} catch (Exception e) {
				e.getMessage();
			}

		}
		return connect;
	}
}