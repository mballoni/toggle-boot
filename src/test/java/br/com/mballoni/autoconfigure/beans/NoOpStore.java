package br.com.mballoni.autoconfigure.beans;

import br.com.mballoni.toggleboot.Store;
import br.com.mballoni.toggleboot.Toggle;

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
