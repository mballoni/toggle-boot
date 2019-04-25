package br.com.mballoni.toggleboot.impl;

import br.com.mballoni.toggleboot.Toggle;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryStoreTest {

    private InMemoryStore subject = new InMemoryStore();

    @Test
    public void should_SAVE_all_given_toggles() {
        List<Toggle> toggles = asList(
                new Toggle("1", true),
                new Toggle("2", true),
                new Toggle("3", false),
                new Toggle("4", true)
        );

        subject.save(toggles);

        assertThat(subject.find("1")).isNotEmpty();
        assertThat(subject.find("2")).isNotEmpty();
        assertThat(subject.find("3")).isNotEmpty();
        assertThat(subject.find("4")).isNotEmpty();
    }
}