package edu.school21.chat.repositories;


import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceCreator {
    private String USERNAME = "postgres";
    private String password = "1qaz";
    private String url = "jdbc:postgresql://localhost:5432/";
    private static HikariDataSource ds;
    public DataSourceCreator() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(USERNAME);
        config.setPassword(password);
        ds = new HikariDataSource(config);
    }
    public DataSource getDataSource() {
       return ds;
    }
}
