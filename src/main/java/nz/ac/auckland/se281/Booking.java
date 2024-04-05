package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  public String ref;
  public String bookDate;
  public String checkIn;
  public String venueCode;
  public String venueName;
  public String email;
  public String attendee;
  public ArrayList<Catering> caters = new ArrayList<Catering>();
  public ArrayList<Music> musics = new ArrayList<Music>();
  public ArrayList<Floral> florals = new ArrayList<Floral>();
  public ArrayList<Service> services = new ArrayList<Service>();
  int numOfServices = 0;

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

  public void addService(Service service) {
    this.services.add(service);
    numOfServices++;
  }
}
