package model.entities;

import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

  private Integer roomNumber;
  private LocalDate checkIn;
  private LocalDate checkOut;

  private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Reservation() {
  }

  public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
    if (!checkOut.isAfter(checkIn)) {
      throw new DomainException("Check-out date must be after check-in date");
    }
    this.roomNumber = roomNumber;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public LocalDate getCheckIn() {
    return checkIn;
  }

  public LocalDate getCheckOut() {
    return checkOut;
  }

  public long duration() {
    return checkOut.toEpochDay() - checkIn.toEpochDay();
  }

  public void updateDates(LocalDate checkIn, LocalDate checkOut) {
    LocalDate now = LocalDate.now();

    if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
      throw new DomainException("Dates must be future");
    }

    if (!checkOut.isAfter(checkIn)) {
      throw new DomainException("Check-out date must be after check-in date");
    }

    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  @Override
  public String toString() {
    return "Reservation: " +
        "Room " + roomNumber +
        ", check-in: " + pattern.format(checkIn) +
        ", check-out: " + pattern.format(checkOut) + ", " +
        duration() + " nights";
  }
}
