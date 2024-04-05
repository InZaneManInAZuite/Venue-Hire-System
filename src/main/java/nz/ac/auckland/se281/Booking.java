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
  public int numOfCaters = 0, numOfMusics = 0, numOfFlorals = 0;

  public Booking() {}

  public Booking(String ref, String checkIn, String email, String venueCode, String attendee) {
    this.ref = ref;
    this.checkIn = checkIn;
    this.email = email;
    this.venueCode = venueCode;
    this.attendee = attendee;
  }

  public void add(Service service) {
    this.services.add(service);
  }

  public void add(Catering cater) {
    this.caters.add(cater);
    numOfCaters++;
  }

  public void add(Music music) {
    this.musics.add(music);
    numOfMusics++;
  }

  public void add(Floral floral) {
    this.florals.add(floral);
    numOfFlorals++;
  }
}
