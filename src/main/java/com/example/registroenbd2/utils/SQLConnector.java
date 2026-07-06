package com.example.registroenbd2.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnector {

    private static HikariDataSource dataSource;

    static {
        //Parte del codigo para buscar la wallet para Oracle
        try {
            ClassLoader classLoader = SQLConnector.class.getClassLoader();
            URL walletUrl = classLoader.getResource("wallet/");

            if (walletUrl == null) {
                throw new RuntimeException("No se encontró la Wallet");
            }

            String walletPath = new File(walletUrl.toURI()).getAbsolutePath();
            walletPath = walletPath.replace("\\", "/");

            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASS");
            String dbName = System.getenv("DB_NAME");

            if (dbUser == null || dbPass == null || dbName == null) {
                Properties creds = new Properties();
                try (InputStream is = classLoader.getResourceAsStream("credentials.properties")) {
                    if (is != null) {
                        creds.load(is);
                        if (dbUser == null) dbUser = creds.getProperty("db.user");
                        if (dbPass == null) dbPass = creds.getProperty("db.pass");
                        if (dbName == null) dbName = creds.getProperty("db.name");
                    }
                }
            }

            //Parte del codigo que sirve para dar a la configuracion de hikari el username y la
            //contraseña, ademas configura el tamaño maximo de la pool
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("oracle.jdbc.OracleDriver");
            config.setJdbcUrl("jdbc:oracle:thin:@" + dbName + "?TNS_ADMIN=" + walletPath);
            config.setUsername(dbUser);
            config.setPassword(dbPass);
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}