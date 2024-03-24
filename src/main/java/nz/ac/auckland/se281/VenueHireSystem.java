package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  // Store all available venue information
  ArrayList<String> venueNames = new ArrayList<String>();
  ArrayList<String> venueCodes = new ArrayList<String>();
  ArrayList<String> capacities = new ArrayList<String>();
  ArrayList<String> hireFees = new ArrayList<String>();
  private int numOfVenues = 0;
  
  // Store all booking information
  ArrayList<String> bookingCode = new ArrayList<String>();
  ArrayList<String> bookingVenue = new ArrayList<String>();
  ArrayList<String> bookingDate = new ArrayList<String>();
  ArrayList<String> bookingEmail = new ArrayList<String>();
  ArrayList<String> bookingOccupants = new ArrayList<String>();
  private int numOfBookings = 0;
  
  // Store all important system information
  private String systemDate = "";

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method

    // Checks if there are venues
    if (numOfVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
    }

    // Print text if number of venues is less than 10
    if (numOfVenues < 10) {
      switch (numOfVenues) {
        case 1:
          MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
          break;
        case 2:
          MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
          break;
        case 3:
          MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
          break;
        case 4:
          MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
          break;
        case 5:
          MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
          break;
        case 6:
          MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
          break;
        case 7:
          MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
          break;
        case 8:
          MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
          break;
        case 9:
          MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
          break;
        default:
          break;
      }
    }

    // If number of venues is 10 or greater
    if (numOfVenues >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(numOfVenues),"s");
    }

    // Print all the venues and their details
    for (int i = 0; i < numOfVenues; i++) {
      MessageCli.VENUE_ENTRY.printMessage(venueNames.get(i), venueCodes.get(i), capacities.get(i), hireFees.get(i), "TODO");
    }

    return;
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
    try {
      int tempCapacity = Integer.parseInt(capacityInput);
      hireFee = tempCapacity;
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }
    if (capacity < 1) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // Tests if hire fee inputted is valid
    try {
      int tempHireFee = Integer.parseInt(hireFeeInput);
      hireFee = tempHireFee;
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    if (hireFee < 1) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    // Checks if venue code is duplicated
    for (int i = 0; i < numOfVenues; i++) {
      if (venueCode.equals(venueCodes.get(i))) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueNames.get(i));
        return;
      }
    }

    // Add venues into the system
    venueNames.add(venueName);
    venueCodes.add(venueCode);
    capacities.add(capacityInput);
    hireFees.add(hireFeeInput);
    numOfVenues++;
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method

    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    // TODO implement this method

    if (systemDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
      return;
    }

    MessageCli.CURRENT_DATE.printMessage(systemDate);
  }

  public void makeBooking(String[] options) {
    // TODO implement this method

    // Obtain booking's venue details
    String venueCode = "";
    String venueName = "";
    int venueCapacity = 0;
    for (int i = 0; i < numOfVenues; i++) {
      if (options[0].equals(venueCodes.get(i))) {
        venueCode = options[0];
        venueName = venueNames.get(i);
        venueCapacity = Integer.parseInt(capacities.get(i));
        break;
      }
    }

    int bookingOccupied = Integer.parseInt(options[3]);



    // Checks if making a booking is possible
    if (systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    if (numOfVenues == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    if (venueCode.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage();
    }
    for (int j = 0; j < numOfBookings; j++){
      if (venueCode.equals(bookingVenue.get(j)) && options[1].equals(bookingDate.get(j))){
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, options[1]);
        bookingIndex = 0;
        return;
      }
    }
    if (venueCapacity < bookingOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], Integer.toString(venueCapacity), Integer.toString(venueCapacity));
      bookingOccupied = venueCapacity;
    }
    int oneForth = (int)(venueCapacity * 0.25);
    if (oneForth > bookingOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], Integer.toString(oneForth), Integer.toString(venueCapacity));
      bookingOccupied = oneForth;
    }



    // Make and confirm booking to user
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    bookingCode.add(bookRef);
    bookingVenue.add(options[0]);
    bookingDate.add(options[1]);
    bookingEmail.add(options[2]);
    bookingOccupants.add(Integer.toString(bookingOccupied));
    numOfBookings++;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, venueName, options[1], options[3]);

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
