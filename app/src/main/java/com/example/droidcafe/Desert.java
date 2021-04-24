package com.example.droidcafe;

public class Desert {
    Long id;
    String name;
    String description;
    Float price;

    @Override
    public String toString() {
        return "Desert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
