package com.darkwater.async;

/**
 * Created by jwc on 2017/7/28.
 */
public enum EventType {
    LOGIN(0),
    LIKE(1),
    COMMENT(3);

    private int value;
    EventType(int value) {
        this.value = value;
    }
    public int getValue() {
        return getValue();
    }
}
