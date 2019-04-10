package com.marceloballoni.autoconfigure;

import com.marceloballoni.toggle.Store;
import com.marceloballoni.toggle.ToggleService;
import com.marceloballoni.toggle.sync.Fetcher;
import com.marceloballoni.toggle.sync.jdbc.JDBCFetcher;
import com.marceloballoni.toggle.sync.Scheduler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
public class ToggleSyncAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "toggle.fetcher", name = "type", havingValue = "JDBC", matchIfMissing = true)
    public class JDBCFetcherConfiguration {

        @Bean
        public Fetcher jdbcFetcher(DataSource dataSource) {
            return new JDBCFetcher(dataSource);
        }

    }

    @Bean
    public Store getStore() {
        return new Store();
    }

    @Bean
    public Scheduler getScheduler(Fetcher fetcher, Store store) {
        return new Scheduler(fetcher, store);
    }

    @Bean
    public ToggleService getToggleService(Store store) {
        return new ToggleService(store);
    }
}
