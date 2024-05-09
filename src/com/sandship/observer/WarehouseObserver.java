package com.sandship.observer;

import com.sandship.Warehouse;
import com.sandship.subject.Subject;

public class WarehouseObserver implements Observer {

    private final Subject warehouseSubject;

    public WarehouseObserver(Subject warehouse) {
        this.warehouseSubject = warehouse;
    }

    @Override
    public void update(Subject subject) {
        if(this.warehouseSubject == subject) {
            System.out.println("Warehouse Observer observes changes in the state of its subject");
            System.out.println(warehouseSubject);
        }
    }
}
