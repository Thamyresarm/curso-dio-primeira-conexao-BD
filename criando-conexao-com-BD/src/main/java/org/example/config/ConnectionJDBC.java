package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionJDBC {

    private ConnectionJDBC() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {

        Connection conn = null;

        try (InputStream input = ConnectionJDBC.class.getClassLoader().getResourceAsStream("connection.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            StringBuilder sb = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName);

            String urlConnection = sb.toString(); //sb.toString() == "jdbc:mysql://localhost/digital_innovation_one"

            try {
                conn = DriverManager.getConnection(urlConnection, user, password);
            } catch(SQLException e) {
                System.out.println("Falha ao tentar realizar a conexão!");
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Falha ao tentar carregar os arquivos!");
            e.printStackTrace();
        }
        return conn;
    }
}
