package main.java.designpatterns.decorator;

public abstract class Beverage {
    String description = "Unknown";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
