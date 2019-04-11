package com.marceloballoni.toggleboot.impl;

import com.marceloballoni.toggleboot.Store;
import com.marceloballoni.toggleboot.Toggle;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
public class InMemoryStore implements Store {

    private final ConcurrentMap<String, Toggle> storage;

    public InMemoryStore() {
        this.storage = new ConcurrentHashMap<>();
    }


    @Override
    public void save(List<Toggle> toggles) {
        log.info("Storing {} toggles", toggles.size());

        Map<String, Toggle> newToggles = toggles.stream()
                .collect(Collectors.toMap(Toggle::getName, t -> t));

        storage.putAll(newToggles);
    }

    @Override
    public Optional<Toggle> find(String name) {
        return Optional.ofNullable(storage.get(name));
    }
}
