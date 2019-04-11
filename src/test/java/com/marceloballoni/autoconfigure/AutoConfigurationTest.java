package com.marceloballoni.autoconfigure;

import com.marceloballoni.toggleboot.Fetcher;
import com.marceloballoni.toggleboot.Store;
import com.marceloballoni.toggleboot.ToggleService;
import com.marceloballoni.toggleboot.impl.InMemoryStore;
import com.marceloballoni.toggleboot.sync.Scheduler;
import com.marceloballoni.toggleboot.sync.jdbc.JDBCFetcher;
import com.marceloballoni.autoconfigure.beans.NoOpFetcher;
import com.marceloballoni.autoconfigure.beans.NoOpStore;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ToggleBootAutoConfiguration.class));

    @Test
    public void setup_DEFAULT_beans() {
        contextRunner.withUserConfiguration(JDBCConfiguration.class)
                .run((context) -> {
                            assertThat(context).hasSingleBean(JDBCFetcher.class);

                            assertThat(context).hasSingleBean(ToggleService.class);

                            assertThat(context).hasSingleBean(InMemoryStore.class);

                            assertThat(context).doesNotHaveBean(Scheduler.class);
                            assertThat(context).doesNotHaveBean(ScheduledAnnotationBeanPostProcessor.class);
                        }
                );
    }

    @Test
    public void setup_SCHEDULER() {
        contextRunner.withUserConfiguration(UserStoreConfiguration.class)
                .withPropertyValues("toggle-boot.auto-synchronize.enabled=true")
                .run((context) -> {
                            assertThat(context).hasSingleBean(Scheduler.class);
                            assertThat(context).hasSingleBean(ScheduledAnnotationBeanPostProcessor.class);
                        }
                );
    }

    @Test
    public void setup_USER_DEFINED_beans_ONLY() {
        contextRunner.withUserConfiguration(UserStoreConfiguration.class)
                .run((context) -> {

                    assertThat(context).hasSingleBean(NoOpFetcher.class);

                    assertThat(context).hasSingleBean(NoOpStore.class);

                });
    }

    @Configuration
    static class UserStoreConfiguration {

        @Bean
        public Fetcher getFetcher() {
            return new NoOpFetcher();
        }

        @Bean
        public Store getStore() {
            return new NoOpStore();
        }

    }

    @Configuration
    @AutoConfigureJdbc
    static class JDBCConfiguration {

    }

}
