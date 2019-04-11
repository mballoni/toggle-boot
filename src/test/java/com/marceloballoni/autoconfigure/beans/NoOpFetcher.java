package com.marceloballoni.autoconfigure.beans;

import com.marceloballoni.toggleboot.Fetcher;
import com.marceloballoni.toggleboot.Toggle;

import java.util.Collections;
import java.util.List;

public class NoOpFetcher implements Fetcher {

    @Override
    public List<Toggle> fetchAll() {
        return Collections.emptyList();
    }
}
