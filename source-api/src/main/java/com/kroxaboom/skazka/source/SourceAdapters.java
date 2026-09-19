package com.kroxaboom.skazka.source;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RU: Небольшой registry code-owned адаптеров. Remote-конфигурация может выбирать данные,
 * но не может подменять исполняемый адаптер.
 *
 * EN: Small registry for code-owned adapters. Remote configuration may select data,
 * but it cannot replace executable adapter code.
 */
public final class SourceAdapters {
    private final LinkedHashMap<String, SourceAdapter> adapters = new LinkedHashMap<>();

    public synchronized void install(SourceAdapter adapter) {
        if (adapter == null || adapter.id() == null || adapter.id().trim().isEmpty()) {
            throw new IllegalArgumentException("Source adapter id is required");
        }

        String id = adapter.id().trim();
        if (adapters.containsKey(id)) {
            throw new IllegalStateException("Source adapter is already installed: " + id);
        }
        adapters.put(id, adapter);
    }

    public synchronized SourceAdapter require(String id) {
        SourceAdapter adapter = adapters.get(id == null ? "" : id.trim());
        if (adapter == null) {
            throw new IllegalStateException("Source adapter is not installed: " + id);
        }
        return adapter;
    }

    public synchronized Map<String, SourceAdapter> all() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(adapters));
    }

    public synchronized void clear() {
        adapters.clear();
    }
}
