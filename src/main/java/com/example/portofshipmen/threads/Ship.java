package com.example.portofshipmen.threads;

import com.almasb.fxgl.entity.Entity;
import com.example.portofshipmen.monitors.Bridge;
import javafx.application.Platform;

public class Ship implements Runnable {
    private Bridge bridge;
    private Entity entity;

    public Ship(Bridge bridge, Entity entity) {
        this.bridge = bridge;
        this.entity = entity;
    }

    @Override
    public void run() {
        try {
            Platform.runLater(() -> {
                entity.setVisible(true);
            });
            Thread.sleep(600);
            System.out.println("Barco llegado");
            bridge.enterShip();
            Platform.runLater(() -> {
                entity.translateY(50);
            });
            System.out.println("Barco cruzando");
            Thread.sleep(2000);
            bridge.exitShip();
            Platform.runLater(() -> {
                entity.translateY(265);
            });
            Thread.sleep(500);
            Platform.runLater(() -> {
                entity.removeFromWorld();
            });
            System.out.println("Barco ha cruzado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}