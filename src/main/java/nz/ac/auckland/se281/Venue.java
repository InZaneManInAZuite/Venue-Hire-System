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

  public int getNumOfVenueBookings() {
    return this.numOfVenueBookings;
  }

  public void setEmptyEarliest(String earliest) {
    if (this.earliest == null) {
      this.earliest = earliest;
    }
  }

  // Check if the date is already booked
  public boolean isDateBooked(String date) {
    for (int i = 0; i < numOfVenueBookings; i++) {
      if (date.equals(bookings.get(i).getCheckIn())) {
        return true;
      }
    }
    return false;
  }

  // Add a booking to the venue
  public void addBooking(Booking booking) {
    bookings.add(booking);
    numOfVenueBookings++;

    if (this.earliest.equals(booking.getCheckIn())) {
      incrementEarliest();
      while (isDateBooked(this.earliest)) {
        incrementEarliest();
      }
    }

    return;
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

    // Convert the month back to a string
    String month = Integer.toString(date.get(Calendar.MONTH));
    if (date.get(Calendar.MONTH) < 10) {
      month = "0" + month;
    }

    // Convert the day back to a string
    String day = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
    if (date.get(Calendar.DAY_OF_MONTH) < 10) {
      day = "0" + day;
    }

    // Convert the date back to a string
    this.earliest = day + "/" + month + "/" + date.get(Calendar.YEAR);
  }

  // Update the earliest available date of the venue
  public void updateEarliest(String newSystemDate) {

    this.earliest = newSystemDate;
    while (isDateBooked(this.earliest)) {
      incrementEarliest();
    }
    return;
  }

  public Booking getBooking(int index) {

    return bookings.get(index);
  }
}
