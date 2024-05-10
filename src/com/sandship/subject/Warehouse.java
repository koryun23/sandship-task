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

    public void addMaterial(Material material, int amountOfMaterials) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(amountOfMaterials < 0) {
            throw new IllegalArgumentException("The amount of materials must be positive");
        }

        int finalCountOfMaterial = getMaterialCount(material) + amountOfMaterials;

        if(isCountExceedingMaxCap(finalCountOfMaterial, material)) finalCountOfMaterial = material.getMaxCapacity();

        materials.put(material, finalCountOfMaterial);

        notifyObservers();
    }

    public void addMaterial(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        addMaterial(material, 1);
    }

    public void removeMaterial(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if (!isMaterialInWarehouse(material)) {
            throw new MaterialNotFoundException(material);
        }
        materials.remove(material);
        notifyObservers();
    }

    public void removeMaterial(Material material, int amountOfMaterials) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(amountOfMaterials < 0) {
            throw new IllegalArgumentException("Material count must be positive");
        }
        if(!isMaterialInWarehouse(material)) {
            throw new MaterialNotFoundException(material);
        }

        int finalMaterialAmount = getMaterialCount(material) - amountOfMaterials;

        if(finalMaterialAmount < 0) {
            materials.remove(material);
        } else {
            materials.put(material, finalMaterialAmount);
        }

        notifyObservers();
    }

    public int getMaterialCount(Material material) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if (isMaterialInWarehouse(material)) {
            return materials.get(material);
        }
        return 0;
    }

    public void transferMaterialTo(Material material, Warehouse warehouse) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(warehouse == null) {
            throw new IllegalArgumentException("Warehouse must not be null");
        }
        transferMaterialTo(material, warehouse, this.getMaterialCount(material));
    }

    public void transferMaterialTo(Material material, Warehouse warehouse, int amountOfMaterials) {
        if(material == null) {
            throw new IllegalArgumentException("Material must not be null");
        }
        if(warehouse == null) {
            throw new IllegalArgumentException("Warehouse must not be null");
        }
        if(amountOfMaterials < 0) {
            throw new IllegalArgumentException("Amount of materials must be positive");
        }
        if(amountOfMaterials > getMaterialCount(material) || amountOfMaterials > material.getMaxCapacity()) {
            throw new IllegalArgumentException(String.format("Transfer amount must be less than the actual amount of that material in the warehouse, but was %s", amountOfMaterials));
        }

        warehouse.addMaterial(material, amountOfMaterials);

        amountOfMaterials = Math.min(amountOfMaterials, material.getMaxCapacity() - getMaterialCount(material));

        this.removeMaterial(material, amountOfMaterials);
        notifyObservers();
    }

    public Material getMaterialByName(String name) {
        for(Map.Entry<Material, Integer> pair : this.materials.entrySet()) {
            if(pair.getKey().getName().equals(name)) {
                return new Material(pair.getKey());
            }
        }
        throw new MaterialNotFoundException(String.format("Material '%s' not found", name));
    }

    private boolean isMaterialInWarehouse(Material material) {
        return materials.containsKey(material);
    }

    private boolean isCountExceedingMaxCap(int count, Material material) {
        return count > material.getMaxCapacity();
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
