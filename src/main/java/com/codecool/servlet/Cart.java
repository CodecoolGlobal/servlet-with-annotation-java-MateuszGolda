package com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public enum Cart {
    INSTANCE;

    private final List<Item> items;

    Cart() {
        items = new ArrayList<>();
    }

    void add(Item item) {
        items.add(item);
    }

    void remove(Item item) {
        items.remove(item);
    }

    List<Item> getItems() {
        return items;
    }

    long getSum() {
        return items.stream().mapToLong(Item::getPrice).sum();
    }
}
