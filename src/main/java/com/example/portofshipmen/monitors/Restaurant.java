package com.example.portofshipmen.monitors;
import com.almasb.fxgl.entity.Entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class Restaurant {
    private Semaphore spaces = new Semaphore(10);
    private AtomicInteger burgers = new AtomicInteger(10);
    private ArrayList<Boolean> parkingSpaces = new ArrayList<>();
    private Entity entity;
    private Boolean isClosed = false;

    public Restaurant() {
        for (int i = 0; i < 10; i++) {
            parkingSpaces.add(false);
        }
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public synchronized int findEmptySpace() {
        for (int i = 0; i < parkingSpaces.size(); i++) {
            if (!parkingSpaces.get(i)) {
                parkingSpaces.set(i, true);
                return i;
            }
        }
        return -1;
    }

    public synchronized void freeSpace(int space) {
        parkingSpaces.set(space, false);
    }

    public synchronized boolean tryEnter() throws InterruptedException {
        if (spaces.tryAcquire()) {
            if (burgers.getAndDecrement() > 0) {
                return true;
            } else {
                spaces.release();
                if (spaces.availablePermits() == 10) {
                    isClosed = true;
                    entity.setVisible(true);
                    System.out.println("Restaurante cerrado para reabastecimiento");
                    Thread.sleep(3000);
                    burgers.set(10);
                    entity.setVisible(false);
                    isClosed = false;
                    System.out.println("Restaurante reabastecido y abierto");
                }
            }
        }
        return false;
    }

    public synchronized boolean isClosed() {
        return isClosed;
    }

    public void exit() {
        spaces.release();
    }
}
