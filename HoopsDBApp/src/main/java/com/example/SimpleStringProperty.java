package com.example;

import javafx.beans.property.StringPropertyBase;

public class SimpleStringProperty extends StringPropertyBase {
    private String value;

    public SimpleStringProperty(String value) {
        this.value = value;
    }

    @Override
    public Object getBean() {
        return null; // Typically the object containing the property
    }

    @Override
    public String getName() {
        return null; // Typically the name of the property
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(String value) {
        this.value = value;
        fireValueChangedEvent();
    }
}
