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
        materials.put(rock, 5);
        materials.put(paper, 10);
        Warehouse warehouse = new Warehouse(materials);

        Observer warehouseObserver = new WarehouseObserver(warehouse);
        warehouse.addMaterial(rock, 5);
    }
}