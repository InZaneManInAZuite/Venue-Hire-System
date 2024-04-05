package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  // Find important details of each instance of a booking
  private String ref;
  private String bookDate;
  private String checkIn;
  private String venueCode;
  private String venueName;
  private String email;
  private String attendee;
  private ArrayList<Service> services = new ArrayList<Service>();
  int numOfServices = 0;

  // Constructors for the booking
  public Booking() {}

  public Booking(
      String ref,
      String checkIn,
      String email,
      String venueCode,
      String attendee,
      String bookDate) {
    this.ref = ref;
    this.checkIn = checkIn;
    this.email = email;
    this.venueCode = venueCode;
    this.attendee = attendee;
    this.bookDate = bookDate;
  }

  // Obtain the details of the booking without the ability to modify them
  public String getRef() {
    return this.ref;
  }

  public String getBookDate() {
    return this.bookDate;
  }

  public String getCheckIn() {
    return this.checkIn;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public String getVenueName() {
    return this.venueName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getAttendee() {
    return this.attendee;
  }

  public ArrayList<Service> getServices() {
    return this.services;
  }

  // Add a service to the booking
  public void addService(Service service) {
    this.services.add(service);
    numOfServices++;
  }
}
