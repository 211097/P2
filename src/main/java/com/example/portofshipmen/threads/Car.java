package com.example.portofshipmen.threads;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.example.portofshipmen.monitors.Bridge;
import com.example.portofshipmen.monitors.Restaurant;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Random;
public class Car implements Runnable {
    private Bridge bridge;
    private Restaurant restaurant;
    private Entity burger;
    private Entity entity;
    private ArrayList<Integer> spacesX = new ArrayList<>();
    private ArrayList<Integer> spacesY = new ArrayList<>();

    public Car(Bridge bridge, Restaurant restaurant, Entity entity, Entity burger) {
        this.bridge = bridge;
        this.restaurant = restaurant;
        this.entity = entity;
        this.burger = burger;

        spacesX.add(465);
        spacesX.add(523);
        spacesX.add(581);
        spacesX.add(407);
        spacesX.add(638);
        spacesX.add(551);
        spacesX.add(491);
        spacesY.add(235);
        spacesY.add(272);
        spacesY.add(310);
    }

    @Override
    public void run() {
        try {
            System.out.println("Carro llegado");
            Thread.sleep(500);
            bridge.enterCar();
            System.out.println("Carro cruzando");
            Platform.runLater(() -> {
                entity.translateX(120);
            });
            Thread.sleep(1000);
            bridge.exitCar();
            System.out.println("Carro ha cruzado");
            Platform.runLater(() -> {
                entity.translateX(240);
            });
            Thread.sleep(500);
                if (restaurant.tryEnter() && !restaurant.isClosed()) {
                    Platform.runLater(() -> {
                        entity.translateY(100);
                    });
                    Thread.sleep(500);
                    int space = restaurant.findEmptySpace();
                    System.out.println("Carro entró al restaurante, estacionado en el espacio " + (space + 1));
                    System.out.println(space);
                    Platform.runLater(() -> {
                        burger.setVisible(true);
                    });
                    if(space == 0 || space == 1 || space == 2){
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space), spacesY.get(0));
                            entity.setPosition(spacesX.get(space), spacesY.get(0));
                        });
                    } else if (space == 4 || space == 5 || space == 6) {
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space - 4), spacesY.get(1));
                            entity.setPosition(spacesX.get(space - 4), spacesY.get(1));
                        });
                    } else if (space == 3) {
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space), spacesY.get(1));
                            entity.setPosition(spacesX.get(space), spacesY.get(1));
                        });
                    } else if (space == 7) {
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space - 3), spacesY.get(1));
                            entity.setPosition(spacesX.get(space-3), spacesY.get(1));
                        });
                    } else if (space == 8) {
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space - 3), spacesY.get(2));
                            entity.setPosition(spacesX.get(space-3), spacesY.get(2));
                        });
                    }else{
                        Platform.runLater(() -> {
                            burger.setPosition(spacesX.get(space - 3), spacesY.get(2));
                            entity.setPosition(spacesX.get(space-3), spacesY.get(2));
                        });
                    }
                    Thread.sleep((new Random().nextInt(6) + 5) * 1000);
                    restaurant.exit();
                    restaurant.freeSpace(space);
                    Platform.runLater(() -> {
                        burger.removeFromWorld();
                        entity.setPosition(665, 150);
                    });
                    Thread.sleep(600);
                    Platform.runLater(() -> {
                        entity.removeFromWorld();
                    });
                    System.out.println("Carro salió del restaurante, espacio " + (space + 1) + " está libre");
                } else {
                    Platform.runLater(() -> {
                        entity.translateX(200);
                    });
                    Thread.sleep(500);
                    Platform.runLater(() -> {
                        entity.removeFromWorld();
                    });
                    System.out.println("Restaurante lleno o cerrado, carro pasó de largo");
                }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}