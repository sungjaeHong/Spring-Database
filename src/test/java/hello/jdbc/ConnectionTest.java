package hello.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;

/**
 * Created by peter on 2022/09/09
 */
@Slf4j
public class ConnectionTest {

    @Test
    void dataSourceDriverManager() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(1000);
        dataSource.setPoolName("MyPool");
        useDataSource(dataSource);

        Thread.sleep(10000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection conn1 = dataSource.getConnection();
        Connection conn2 = dataSource.getConnection();
        log.info("connection={} class={}", conn1, conn1.getClass());
        log.info("connection={} class={}", conn2, conn2.getClass());
    }
}
