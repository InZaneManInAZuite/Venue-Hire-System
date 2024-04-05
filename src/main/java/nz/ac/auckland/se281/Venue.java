package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Venue {
  // Find important details of each instance of a venue
  private String name;
  private String code;
  private String capacity;
  private String hireFee;
  private String earliest;
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private int numOfVenueBookings = 0;

  // Constructors for the venue
  public Venue() {}

  public Venue(String name, String code, String capacity, String hireFee) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  // Obtain the details of the venue without the ability to modify them
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

  public void setEarliest(String earliest) {
    if (this.earliest == null) {
      this.earliest = earliest;
    }
  }

  // Check if the date is already booked
  public boolean isDateBooked(String date) {
    for (int i = 0; i < numOfVenueBookings; i++) {
      if (date.equals(bookings.get(i).checkIn)) {
        return true;
      }
    }
    return false;
  }

  // Add a booking to the venue
  public void addBooking(Booking booking) {
    bookings.add(booking);
    numOfVenueBookings++;

    while (isDateBooked(this.earliest)) {
      incrementEarliest();
    }
  }

  // Modify the earliest available date of the venue
  public void incrementEarliest() {

    // Split the date into day, month and year
    Calendar date = new GregorianCalendar();
    String[] dateSplit = this.earliest.split("/");
    date.set(
        Integer.parseInt(dateSplit[2]),
        Integer.parseInt(dateSplit[1]),
        Integer.parseInt(dateSplit[0]));

    // Increment the date by 1 day
    date.add(Calendar.DAY_OF_MONTH, 1);

    // Convert the date back to a string
    this.earliest =
        date.get(Calendar.DAY_OF_MONTH)
            + "/"
            + date.get(Calendar.MONTH)
            + "/"
            + date.get(Calendar.YEAR);

    // Add a 0 to the day if it is less than 10
    if (date.get(Calendar.DAY_OF_MONTH) < 10) {
      this.earliest = "0" + this.earliest;
    }
  }
}
