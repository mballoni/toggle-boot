package br.com.mballoni.toggleboot.impl;

import br.com.mballoni.toggleboot.Toggle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
@Import(JDBCFetcherIT.JDBCConfiguration.class)
public class JDBCFetcherIT {

    @Autowired
    private JdbcTemplate template;

    private JDBCFetcher subject;


    @Before
    public void setUp() {
        subject = new JDBCFetcher(template.getDataSource());
        setField(subject, "toggleQuery", "SELECT toggle_name, active FROM toggles");
    }

    @Test
    public void should_fetch_ALL_toggles_from_DATABASE() {
        template.update("INSERT INTO toggles (toggle_name, active) VALUES ('feature_1', 1)");
        template.update("INSERT INTO toggles (toggle_name, active) VALUES ('feature_N', 0)");

        List<Toggle> toggles = subject.fetchAll();

        assertThat(toggles)
                .extracting("name", "active")
                .containsExactlyInAnyOrder(
                        tuple("feature_1", true),
                        tuple("feature_N", false)
                );

    }


    @Configuration
    @AutoConfigureJdbc
    static class JDBCConfiguration {

    }
}