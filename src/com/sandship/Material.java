package com.sandship;

import java.util.Objects;

public class Material {

    private String name;
    private String description;
    private String icon;
    private int maxCapacity;

    public Material(String name, String description, String icon, int maxCapacity) {
        setName(name);
        setDescription(description);
        setIcon(icon);
        setMaxCapacity(maxCapacity);
    }

    public Material(Material material) {
        setName(material.getName());
        setDescription(material.getDescription());
        setIcon(material.getIcon());
        setMaxCapacity(material.getMaxCapacity());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Material name must not be null");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) throw new IllegalArgumentException("Material description must not be null");
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        if(icon == null) throw new IllegalArgumentException("Material icon must not be null");
        this.icon = icon;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if(maxCapacity < 0) throw new IllegalArgumentException(String.format("Material maximum capacity must be a positive number, not %d", maxCapacity));
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return maxCapacity == material.maxCapacity && Objects.equals(name, material.name) && Objects.equals(description, material.description) && Objects.equals(icon, material.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, icon, maxCapacity);
    }

    @Override
    public String toString() {
        return "com.sandship.Material{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
