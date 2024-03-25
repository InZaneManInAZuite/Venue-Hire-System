package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Venue {
  private String name;
  private String venueCode;
  private String capacity;
  private String hireFee;
  private String earliest;
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private int numOfVenueBookings = 0;

  public Venue() {}

  public Venue(String name, String venueCode, String capacity, String hireFee) {
    this.name = name;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public boolean isDateBooked (String date) {
    for (int i = 0; i < numOfVenueBookings; i++) {
      if (date.equals(bookings.get(i).checkIn)) {
        return true;
      }
    }
    return false;
  }

  public void add (Booking booking) {
    bookings.add(booking);
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
  }

}
