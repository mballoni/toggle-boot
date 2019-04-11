package com.marceloballoni.toggleboot;

import com.marceloballoni.toggleboot.exception.ToggleUnavailableException;

import static java.lang.String.format;

public class ToggleService {

    private Store store;

    public ToggleService(Store store) {
        this.store = store;
    }

    public boolean isEnabled(String name) throws ToggleUnavailableException {
        return store.find(name)
                .map(Toggle::isActive)
                .orElseThrow(() -> new ToggleUnavailableException(format("Toggle %s not found", name)));
    }
}
