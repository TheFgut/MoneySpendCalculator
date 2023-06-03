package com.example.moneyspendcalculator.ObserverPattern;

import java.util.ArrayList;

public class Observerable {
    private ArrayList<Observer> channels = new ArrayList<>();

    public void addObserver(Observer channel) {
        this.channels.add(channel);
    }

    public void removeObserver(Observer channel) {
        this.channels.remove(channel);
    }

    public void Invoke() {
        for (Observer channel : this.channels) {
            channel.update();
        }
    }
}
