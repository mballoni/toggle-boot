package com.marceloballoni.toggle.sync;

import com.marceloballoni.toggle.Store;
import com.marceloballoni.toggle.Toggle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class Scheduler {

    private Fetcher fetcher;
    private Store store;

    public Scheduler(Fetcher fetcher, Store store) {
        this.fetcher = fetcher;
        this.store = store;
    }


    @Transactional
    @Scheduled(fixedDelayString = "${toggle.fetcher.frequency:5000}")
    public void execute() {
        List<Toggle> toggles = fetcher.fetchAll();
        store.save(toggles);
    }
}
