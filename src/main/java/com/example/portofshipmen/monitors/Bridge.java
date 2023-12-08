package com.example.portofshipmen.monitors;
import java.util.concurrent.atomic.AtomicInteger;

public class Bridge {
    private AtomicInteger carsOnBridge = new AtomicInteger(0);
    private AtomicInteger carCount = new AtomicInteger(0);
    private AtomicInteger shipsOnBridge = new AtomicInteger(0);

    public synchronized void enterCar() throws InterruptedException {
        while (carCount.get() >= 3 || shipsOnBridge.get() > 0) {
            wait();
        }
        carsOnBridge.incrementAndGet();
    }

    public synchronized void exitCar() {
        carsOnBridge.decrementAndGet();
        carCount.incrementAndGet();
        if (carCount.get() == 3) {
            notifyAll();
        }
    }

    public synchronized void enterShip() throws InterruptedException {
        while (carCount.get() < 3 || carsOnBridge.get() > 0) {
            wait();
        }
        shipsOnBridge.incrementAndGet();
    }

    public synchronized void exitShip() {
        shipsOnBridge.decrementAndGet();
        carCount.set(0);
        notifyAll();
    }
}