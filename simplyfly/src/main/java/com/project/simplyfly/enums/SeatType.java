package com.project.simplyfly.enums;

public enum SeatType {
	WINDOW(20),
    MIDDLE(0),
    AISLE(10);
private int value;

private SeatType(int value) {
	this.value = value;
}

public int getValue() {
	return value;
}
	

}
