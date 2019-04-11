package com.marceloballoni.autoconfigure.beans;

import com.marceloballoni.toggleboot.Store;
import com.marceloballoni.toggleboot.Toggle;

import java.util.List;
import java.util.Optional;

public class NoOpStore implements Store {

    @Override
    public void save(List<Toggle> toggles) {

    }

    @Override
    public Optional<Toggle> find(String name) {
        return Optional.empty();
    }
}
