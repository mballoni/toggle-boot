package com.marceloballoni.toggleboot.impl;

import com.marceloballoni.toggleboot.Fetcher;
import com.marceloballoni.toggleboot.Toggle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class JDBCFetcher implements Fetcher {

    private final JdbcTemplate jdbcTemplate;

    @Value("${toggle-boot.fetcher.jdbc.query:SELECT toggle_name, active FROM toggles}")
    private String toggleQuery;

    public JDBCFetcher(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Toggle> fetchAll() {
        log.info("Fetching all togles");

        return jdbcTemplate.query(toggleQuery, (rs, rowNum) -> new Toggle(
                rs.getString("toggle_name"),
                rs.getBoolean("active")
        ));
    }
}
