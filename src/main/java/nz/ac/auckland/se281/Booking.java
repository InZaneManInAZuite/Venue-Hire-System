package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Booking {
  private String ref;
  private String bookDate;
  public String checkIn;
  private String venueCode;
  private String venueName;
  private String email;
  private String attendee;
  private ArrayList<Catering> caters = new ArrayList<Catering>();
  private ArrayList<Music> musics = new ArrayList<Music>();
  private ArrayList<Floral> florals = new ArrayList<Floral>();
  private int numOfCaters = 0, numOfMusics = 0, numOfFlorals = 0;


  public Booking() {}
}
