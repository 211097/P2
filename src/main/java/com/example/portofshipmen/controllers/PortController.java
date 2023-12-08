package com.example.portofshipmen.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.example.portofshipmen.entities.BuilderFactory;
import com.example.portofshipmen.monitors.Bridge;
import com.example.portofshipmen.monitors.Restaurant;
import com.example.portofshipmen.threads.Car;
import com.example.portofshipmen.threads.Ship;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PortController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameWorld gameWorld = FXGL.getGameWorld();
        Random rand = new Random();
        gameWorld.addEntityFactory(new BuilderFactory());
        Bridge bridge = new Bridge();
        Restaurant restaurant = new Restaurant();
        Platform.runLater(()->{
            Entity entity = FXGL.spawn("Banner", 500, 168);
            entity.setVisible(false);
            restaurant.setEntity(entity);
        });
        new Thread(()->{
            for(int i = 0; i < 100; i++) {
                try {
                    Thread.sleep((rand.nextInt(5 - 3 + 1) + 3) * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(()->{
                    Entity entity, burger;
                    entity = FXGL.spawn("Car", 30, 50);
                    burger = FXGL.spawn("Burger", 30, 50);
                    burger.setVisible(false);
                    Car car = new Car(bridge, restaurant, entity, burger);
                    new Thread(car).start();
                });
            }
        }).start();

        new Thread(()->{
            for(int i = 0; i < 100; i++) {
                try {
                    Thread.sleep((rand.nextInt(6 - 4 + 1) + 4) * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(()->{
                    Entity entity;
                    entity = FXGL.spawn("Ship", 115, -15);
                    entity.setVisible(false);
                    Ship ship = new Ship(bridge, entity);
                    new Thread(ship).start();
                });
            }
        }).start();
    }
}