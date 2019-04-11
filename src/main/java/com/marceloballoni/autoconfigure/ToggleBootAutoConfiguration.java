package com.marceloballoni.autoconfigure;

import com.marceloballoni.toggleboot.Fetcher;
import com.marceloballoni.toggleboot.Store;
import com.marceloballoni.toggleboot.ToggleService;
import com.marceloballoni.toggleboot.impl.InMemoryStore;
import com.marceloballoni.toggleboot.sync.Scheduler;
import com.marceloballoni.toggleboot.sync.jdbc.JDBCFetcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
public class ToggleBootAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(value = Fetcher.class)
    public class JDBCFetcherConfiguration {

        @Bean
        @ConditionalOnProperty(prefix = "toggle-boot.fetcher", name = "type", havingValue = "JDBC", matchIfMissing = true)
        public Fetcher jdbcFetcher(DataSource dataSource) {
            return new JDBCFetcher(dataSource);
        }
    }

    @Configuration
    @EnableScheduling
    @ConditionalOnProperty(prefix = "toggle-boot.auto-synchronize", name = "enabled", havingValue = "true", matchIfMissing = false)
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
