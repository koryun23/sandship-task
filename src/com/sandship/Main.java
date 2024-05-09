package com.sandship;

import com.sandship.observer.Observer;
import com.sandship.observer.WarehouseObserver;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Material rock = new Material(
                "Rock",
                "Description of Rock",
                "rock-icon.png",
                100
        );
        Material paper = new Material(
                "Paper",
                "Description of Paper",
                "paper-icon.png",
                200
        );
        Map<Material, Integer> materials = new HashMap<>();
        materials.put(rock, 1);
        materials.put(paper, 1);
        Warehouse warehouse = new Warehouse(materials);
        Warehouse another = new Warehouse(new HashMap<>(materials));
        Observer warehouseObserver = new WarehouseObserver(warehouse);
        Observer anotherObserver = new WarehouseObserver(another);
        //warehouse.addMaterial(rock, 5);
        warehouse.transferMaterialTo(rock, another);
    }
}