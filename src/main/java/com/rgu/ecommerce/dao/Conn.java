
package com.rgu.ecommerce.dao;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Shriram Prajapat
 */
public class Conn {

    private static final DBParams DB_PARAMS = new DBParams(DBServer.MY_SQL, "localhost", "tms", "root", "sql1");
    
    private static final HikariDataSource DATA_SOURCE1;
    private static final HikariConfig HIKARI_CONFIG1 = new HikariConfig();
    
    
    static {
            //createDatabase();
            HIKARI_CONFIG1.setJdbcUrl(DB_PARAMS.getConnectionPath());
            HIKARI_CONFIG1.setUsername(DB_PARAMS.getUsername());
            HIKARI_CONFIG1.setPassword(DB_PARAMS.getPassword());
            HIKARI_CONFIG1.setMaximumPoolSize(40);
            HIKARI_CONFIG1.addDataSourceProperty("cachePrepStmts", true);
            HIKARI_CONFIG1.addDataSourceProperty("prepStmtCacheSize", 250);
            HIKARI_CONFIG1.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            HIKARI_CONFIG1.addDataSourceProperty("useServerPrepStmts", true);
            HIKARI_CONFIG1.setConnectionTestQuery("SELECT 1");
            HIKARI_CONFIG1.addDataSourceProperty("characterEncoding","utf8"); //tried with UTF-8 as well
            HIKARI_CONFIG1.addDataSourceProperty("useUnicode","true");
            DATA_SOURCE1 = new HikariDataSource(HIKARI_CONFIG1);
            
            System.out.println("Hikari connection pool ready..."); 
    }
    
    public static HikariDataSource getDataSource() {
        return DATA_SOURCE1;
    }

    public static Connection getConnection() throws SQLException{
        return  getDataSource().getConnection();
    }
  
}
