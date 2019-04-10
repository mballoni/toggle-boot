package com.marceloballoni.toggle;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
public class Store {

    private final ConcurrentMap<String, Toggle> storage;

    public Store() {
        this.storage = new ConcurrentHashMap<>();
    }


    public void save(List<Toggle> toggles) {
        log.info("Storing {} toggles", toggles.size());

        Map<String, Toggle> newToggles = toggles.stream()
                .collect(Collectors.toMap(Toggle::getName, t -> t));

        storage.putAll(newToggles);
    }

    public Optional<Toggle> find(String name) {
        return Optional.ofNullable(storage.get(name));
    }
}
