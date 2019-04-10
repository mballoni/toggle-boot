package com.marceloballoni.toggle.sync;

import com.marceloballoni.toggle.Toggle;

import java.util.List;

public interface Fetcher {
    List<Toggle> fetchAll();
}
