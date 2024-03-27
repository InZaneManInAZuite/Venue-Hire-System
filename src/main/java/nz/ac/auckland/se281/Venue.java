package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Venue {
  private String name;
  private String code;
  private String capacity;
  private String hireFee;
  private String earliest;
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private int numOfVenueBookings = 0;

  public Venue() {}

  public Venue(String name, String code, String capacity, String hireFee) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String getName() {
    return this.name;
  }

  public String getCode() {
    return this.code;
  }

  public String getCapacity() {
    return this.capacity;
  }

  public String getHireFee() {
    return this.hireFee;
  }

  public String getEarliest() {
    return this.earliest;
  }

  public boolean isDateBooked (String date) {
    for (int i = 0; i < numOfVenueBookings; i++) {
      if (date.equals(bookings.get(i).checkIn)) {
        return true;
      }
    }
    return false;
  }

  public void addBooking (Booking booking) {
    bookings.add(booking);
    numOfVenueBookings++;
    while (isDateBooked(this.earliest)) {
      incrementEarliest();
    }
  }

  public void incrementEarliest() {
    Calendar date = new GregorianCalendar();
    String[] dateSplit = this.earliest.split("/");
    date.set( Integer.parseInt(dateSplit[2]), 
              Integer.parseInt(dateSplit[1]), 
              Integer.parseInt(dateSplit[0]));
    date.add(Calendar.DAY_OF_MONTH, 1);

    
    this.earliest = date.get(Calendar.DAY_OF_MONTH) + "/" +
                    date.get(Calendar.MONTH) + "/" +
                    date.get(Calendar.YEAR);
  }

}
