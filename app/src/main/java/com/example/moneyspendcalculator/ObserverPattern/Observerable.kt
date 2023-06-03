package com.example.moneyspendcalculator.ObserverPattern

class Observerable {
    private val channels = ArrayList<Observer>()
    fun addObserver(channel: Observer) {
        channels.add(channel)
    }

    fun removeObserver(channel: Observer) {
        channels.remove(channel)
    }

    fun Invoke() {
        for (channel in channels) {
            channel.update()
        }
    }
}