package com.sandship;

import com.sandship.exceptions.MaterialNotFoundException;
import com.sandship.subject.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Warehouse extends Subject {

    private final Map<Material, Integer> materials;

    public Warehouse(Map<Material, Integer> materials) {
        super(new ArrayList<>());
        this.materials = materials;
    }

    public Warehouse() {
        super(new ArrayList<>());
        this.materials = new HashMap<>();
    }

    public void addMaterial(Material material, int count) {
        int initialCountOfMaterial = 0;
        if (materials.containsKey(material)) {
            initialCountOfMaterial = materials.get(material);
        }
        int finalCountOfMaterial = initialCountOfMaterial + count;
        if(finalCountOfMaterial > material.getMaxCapacity()) finalCountOfMaterial = material.getMaxCapacity();
        materials.put(material, finalCountOfMaterial);
        System.out.println(finalCountOfMaterial);
        notifyObservers();
    }

    public void addMaterial(Material material) {
        addMaterial(material, 1);
    }

    public void removeMaterial(Material material) {
        if (materials.containsKey(material)) {
            materials.remove(material);
            notifyObservers();
            return;
        }

        throw new MaterialNotFoundException(material);
    }

    public int getMaterialCount(Material material) {
        if (materials.containsKey(material)) {
            notifyObservers();
            return materials.get(material);
        }
        throw new MaterialNotFoundException(material);
    }

    public void transferMaterialTo(Material material, Warehouse warehouse) {
        warehouse.addMaterial(material, warehouse.getMaterialCount(material));
        this.removeMaterial(material);
        notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WAREHOUSE");
        sb.append("\n-----------------------\n");
        for(Map.Entry<Material, Integer> entry : this.materials.entrySet()) {
            sb.append(entry.getKey().toString());
            sb.append("Current Amount: ");
            sb.append(entry.getValue());
            sb.append("\n-----------------------\n");
        }
        return sb.toString();
    }
}
