package com.project.simplyfly.dto;

import java.util.List;

import com.project.simplyfly.enums.SeatType;

public class SeatSelectionDto {
	private int flightId;
    private List<Integer> passengerIds;
    public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public List<Integer> getPassengerIds() {
		return passengerIds;
	}
	public void setPassengerIds(List<Integer> passengerIds) {
		this.passengerIds = passengerIds;
	}
	public List<String> getSeatNumbers() {
		return seatNumbers;
	}
	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}
	public List<SeatType> getSeatTypes() {
		return seatTypes;
	}
	public void setSeatTypes(List<SeatType> seatTypes) {
		this.seatTypes = seatTypes;
	}
	private List<String> seatNumbers;
    private List<SeatType> seatTypes;

}

	

