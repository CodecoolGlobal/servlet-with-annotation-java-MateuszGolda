package com.codecool.servlet;

public class Item {
    final private int id;
    final private String name;
    final private long price;

    public Item(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    long getPrice() {
        return price;
    }

    int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Item item = (Item) o;
        return id == item.id &&
                price == item.price &&
                name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
