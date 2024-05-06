package com.sandship;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final Map<Material, Integer> materials;

    public Warehouse(Map<Material, Integer> materials) {
        this.materials = materials;
    }

    public Warehouse() {
        this.materials = new HashMap<>();
    }

    public void addMaterial(Material material, int count) {
        int initialCountOfMaterial = 0;
        if (materials.containsKey(material)) {
            initialCountOfMaterial = materials.get(material);
        }
        materials.put(material, initialCountOfMaterial + count);
    }

    public void addMaterial(Material material) {
        addMaterial(material, 1);
    }

    public void removeMaterial(Material material, int count) {
        if (materials.containsKey(material)) {
            materials.put(material, materials.get(material) - count);
        } else {
            // throw exception
        }

    }

    public void removeMaterial(Material material) {

    }

    public int getMaterialCount(Material material) {
        return 0;
    }
}
