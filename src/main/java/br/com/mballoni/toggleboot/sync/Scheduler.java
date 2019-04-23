package br.com.mballoni.toggleboot.sync;

import br.com.mballoni.toggleboot.Store;
import br.com.mballoni.toggleboot.Toggle;
import br.com.mballoni.toggleboot.Fetcher;
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
