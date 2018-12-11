package com.webserver.db;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class MemoryDB<T> {
    protected Map<String, T> indexedRows;

    protected MemoryDB() {
        this.indexedRows = new HashMap<>();
    }

    public Map<String, T> getAll() {
        return this.indexedRows;
    }

    public T getBy(Predicate<T> predicate) {
        T lookedForEntry = null;

        for (Map.Entry<String, T> entry : this.indexedRows.entrySet()) {
            if (predicate.test(entry.getValue())) {
                lookedForEntry = entry.getValue();
                break;
            }
        }

        return lookedForEntry;
    }

    public boolean insert(String id, T item) {
        return this.indexedRows.put(id, item) != null;
    }
}
