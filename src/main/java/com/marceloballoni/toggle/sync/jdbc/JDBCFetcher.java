package com.marceloballoni.toggle.sync.jdbc;

import com.marceloballoni.toggle.Toggle;
import com.marceloballoni.toggle.sync.Fetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class JDBCFetcher implements Fetcher {

    private final JdbcTemplate jdbcTemplate;

    @Value("${toggle.query:SELECT name, active FROM TOGGLES}")
    private String toggleQuery;

    public JDBCFetcher(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Toggle> fetchAll() {
        log.info("Fetching all togles");

        return jdbcTemplate.query(toggleQuery, (rs, rowNum) -> new Toggle(
                rs.getString("name"),
                rs.getBoolean("active")
        ));
    }
}
