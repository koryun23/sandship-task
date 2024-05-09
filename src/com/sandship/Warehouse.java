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

    public void addMaterial(Material material, int count, boolean notifyObservers) {
        int initialCountOfMaterial = 0;
        if (materials.containsKey(material)) {
            initialCountOfMaterial = materials.get(material);
        }
        int finalCountOfMaterial = initialCountOfMaterial + count;
        if(finalCountOfMaterial > material.getMaxCapacity()) finalCountOfMaterial = material.getMaxCapacity();
        materials.put(material, finalCountOfMaterial);

        if(notifyObservers) notifyObservers();
    }

    public void addMaterial(Material material, int count) {
        addMaterial(material,  count, true);
    }

    public void addMaterial(Material material) {
        addMaterial(material, 1);
    }

    public void removeMaterial(Material material, boolean notifyObservers) {
        if (!materials.containsKey(material)) {
            throw new MaterialNotFoundException(material);
        }
        materials.remove(material);
        if(notifyObservers) notifyObservers();
    }

    public void removeMaterial(Material material) {
        removeMaterial(material, true);
    }

    public int getMaterialCount(Material material, boolean notifyObservers) {
        if (materials.containsKey(material)) {
            if(notifyObservers) notifyObservers();
            return materials.get(material);
        }
        return 0;
    }

    public int getMaterialCount(Material material) {
        return getMaterialCount(material, true);
    }

    public void transferMaterialTo(Material material, Warehouse warehouse) {
        transferMaterialTo(material, warehouse, true);
    }

    public void transferMaterialTo(Material material, Warehouse warehouse, boolean notifyObservers) {
        warehouse.addMaterial(material, warehouse.getMaterialCount(material), false);
        this.removeMaterial(material, false);
        if(notifyObservers) notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WAREHOUSE");
        sb.append("\n----------------------------------------\n");
        for(Map.Entry<Material, Integer> entry : this.materials.entrySet()) {
            sb.append(entry.getKey().toString());
            sb.append("Current Amount: ");
            sb.append(entry.getValue());
            sb.append("\n-----------------------\n");
        }
        sb.append("\n----------------------------------------\n");
        return sb.toString();
    }
}
