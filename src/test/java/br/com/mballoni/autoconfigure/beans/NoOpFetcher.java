package br.com.mballoni.autoconfigure.beans;

import br.com.mballoni.toggleboot.Toggle;
import br.com.mballoni.toggleboot.Fetcher;

import java.util.Collections;
import java.util.List;

public class NoOpFetcher implements Fetcher {

    @Override
    public List<Toggle> fetchAll() {
        return Collections.emptyList();
    }
}
