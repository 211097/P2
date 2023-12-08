package com.example.portofshipmen.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.example.portofshipmen.entities.types.Types;

public class BuilderFactory implements EntityFactory {
    @Spawns("Car")
    public Entity newCar(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Types.CAR)
                .view("car.png")
                .build();
    }

    @Spawns("Ship")
    public Entity newShip(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Types.SHIP)
                .view("ship.png")
                .build();
    }

    @Spawns("Burger")
    public Entity newBurger(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Types.BURGER)
                .view("burger.png")
                .build();
    }

    @Spawns("Banner")
    public Entity newBanner(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Types.BANNER)
                .view("banner.png")
                .build();
    }
}
