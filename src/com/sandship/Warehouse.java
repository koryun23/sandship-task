package com.sandship;

import com.sandship.exceptions.MaterialNotFoundException;

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

    public void removeMaterial(Material material) {
        if (materials.containsKey(material)) {
            materials.remove(material);
            return;
        }

        throw new MaterialNotFoundException(material);
    }

    public int getMaterialCount(Material material) {
        if (materials.containsKey(material)) {
            return materials.get(material);
        }
        throw new MaterialNotFoundException(material);
    }
}
