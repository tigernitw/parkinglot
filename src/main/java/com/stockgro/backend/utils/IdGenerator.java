package com.stockgro.backend.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private final AtomicInteger currentId = new AtomicInteger(0);

    public int getNextId() {
        return currentId.incrementAndGet();
    }
}

