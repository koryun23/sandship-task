package com.sandship.observer;

import com.sandship.subject.Subject;

public class WarehouseObserver implements Observer {

    private final Subject warehouseSubject;

    public WarehouseObserver(Subject warehouse) {
        this.warehouseSubject = warehouse;
        warehouseSubject.attach(this);
    }

    @Override
    public void update(Subject subject) {
        if (this.warehouseSubject == subject) {
            System.out.println(warehouseSubject);
        }
    }
}
