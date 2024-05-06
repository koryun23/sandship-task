package com.sandship.exceptions;

import com.sandship.Material;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(Material material) {
        super(String.format("Material %s is not registered in the warehouse", material.toString()));
    }

    public MaterialNotFoundException(String msg) {
        super(msg);
    }
}
