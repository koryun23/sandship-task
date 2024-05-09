package com.sandship.subject;

import com.sandship.core.Material;
import com.sandship.exceptions.MaterialNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(count < 0) {
            throw new IllegalArgumentException("The amount of materials must be positive");
        }
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
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(count < 0) {
            throw new IllegalArgumentException("The amount of materials must be positive");
        }
        addMaterial(material,  count, true);
    }

    public void addMaterial(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        addMaterial(material, 1);
    }

    public void addMaterial(Material material, boolean notifyObservers) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        addMaterial(material, 1, notifyObservers);
    }

    public void removeMaterial(Material material, boolean notifyObservers) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if (!materials.containsKey(material)) {
            throw new MaterialNotFoundException(material);
        }
        materials.remove(material);
        if(notifyObservers) notifyObservers();
    }

    public void removeMaterial(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        removeMaterial(material, true);
    }

    public int getMaterialCount(Material material, boolean notifyObservers) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if (materials.containsKey(material)) {
            if(notifyObservers) notifyObservers();
            return materials.get(material);
        }
        return 0;
    }

    public int getMaterialCount(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        return getMaterialCount(material, false);
    }

    public void transferMaterialTo(Material material, Warehouse warehouse) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(warehouse == null) {
            throw new IllegalArgumentException("Warehouse must not be null");
        }
        transferMaterialTo(material, warehouse, true);
    }

    public void transferMaterialTo(Material material, Warehouse warehouse, boolean notifyObservers) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(warehouse == null) {
            throw new IllegalArgumentException("Warehouse must not be null");
        }
        warehouse.addMaterial(material, warehouse.getMaterialCount(material), notifyObservers);
        this.removeMaterial(material, notifyObservers);
        if(notifyObservers) notifyObservers();
    }

    public Map<Material, Integer> getMaterials() {
        return new HashMap<>(materials);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(materials, warehouse.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(materials);
    }
}
