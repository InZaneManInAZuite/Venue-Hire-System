package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  ArrayList<String> venueNames = new ArrayList<String>();
  ArrayList<String> venueCodes = new ArrayList<String>();
  ArrayList<Integer> capacities = new ArrayList<Integer>();
  ArrayList<Integer> hireFees = new ArrayList<Integer>();

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method

    // Checks if there are venues
    if (hireFees.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this 
    
    int capacity = Integer.parseInt(capacityInput);
    int hireFee;

    // Tests if there is a venue name
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Tests if capacity inputted is valid
    if (capacity < 1) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // Tests if hire fee inputted is valid
    try {
      int tempHireFee = Integer.parseInt(hireFeeInput);
      hireFee = tempHireFee;
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    venueNames.add(venueName);
    venueCodes.add(venueCode);
    capacities.add(capacity);
    hireFees.add(hireFee);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
