package com.marceloballoni.autoconfigure;

import com.marceloballoni.toggle.Fetcher;
import com.marceloballoni.toggle.Store;
import com.marceloballoni.toggle.ToggleService;
import com.marceloballoni.toggle.impl.InMemoryStore;
import com.marceloballoni.toggle.sync.Scheduler;
import com.marceloballoni.toggle.sync.jdbc.JDBCFetcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
public class ToggleSyncAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "toggle.fetcher", name = "type", havingValue = "JDBC", matchIfMissing = true)
    public class JDBCFetcherConfiguration {

        @Bean
        public Fetcher jdbcFetcher(DataSource dataSource) {
            return new JDBCFetcher(dataSource);
        }
    }

    @Configuration
    @EnableScheduling
    @ConditionalOnProperty(prefix = "toggle.auto-synchronize", name = "enabled", havingValue = "true", matchIfMissing = false)
    public class SchedulerAutoConfiguration {

        @Bean
        public Scheduler getScheduler(Fetcher fetcher, Store store) {
            return new Scheduler(fetcher, store);
        }
    }

    @Bean
    @ConditionalOnMissingBean(value = Store.class)
    public Store getStore() {
        return new InMemoryStore();
    }


    @Bean
    public ToggleService getToggleService(Store store) {
        return new ToggleService(store);
    }
}
