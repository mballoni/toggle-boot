package com.marceloballoni.toggleboot;

import java.util.List;
import java.util.Optional;

public interface Store {
    void save(List<Toggle> toggles);

    Optional<Toggle> find(String name);
}
