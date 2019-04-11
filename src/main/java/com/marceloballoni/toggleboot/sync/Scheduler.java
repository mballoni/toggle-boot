package com.marceloballoni.toggleboot.sync;

import com.marceloballoni.toggleboot.Fetcher;
import com.marceloballoni.toggleboot.Store;
import com.marceloballoni.toggleboot.Toggle;
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
    @Scheduled(fixedDelayString = "${toggle-boot.synchronizer.frequency:5000}")
    public void execute() {
        List<Toggle> toggles = fetcher.fetchAll();
        store.save(toggles);
    }
}
