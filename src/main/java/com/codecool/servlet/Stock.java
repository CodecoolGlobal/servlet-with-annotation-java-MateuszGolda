package com.codecool.servlet;

import java.util.HashSet;
import java.util.Set;

public class Stock {
    final private Set<Item> items;

    public Stock() {
        items = new HashSet<>();
    }

    void addItem(Item item) {
        items.add(item);
    }

    Set<Item> getItems() {
        return items;
    }

    Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) return item;
        }
        throw new NullPointerException("Couldn't find item with given id.");
    }
}
