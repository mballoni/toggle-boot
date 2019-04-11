package com.marceloballoni.autoconfigure.beans;

import com.marceloballoni.toggle.Fetcher;
import com.marceloballoni.toggle.Toggle;

import java.util.Collections;
import java.util.List;

public class NoOpFetcher implements Fetcher {

    @Override
    public List<Toggle> fetchAll() {
        return Collections.emptyList();
    }
}
