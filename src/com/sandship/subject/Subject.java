package com.sandship.subject;

import com.sandship.observer.Observer;

import java.util.List;

public abstract class Subject {

    private List<Observer> observers;

    public Subject(List<Observer> observers) {
        this.observers = observers;
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }
}
