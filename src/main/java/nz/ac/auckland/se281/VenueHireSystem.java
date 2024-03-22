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
  private int venueIndex = 0;

  // Store all booking information
  ArrayList<String> bookingCode = new ArrayList<String>();
  ArrayList<String> bookingVenue = new ArrayList<String>();
  ArrayList<String> bookingDate = new ArrayList<String>();
  ArrayList<String> bookingEmail = new ArrayList<String>();
  ArrayList<String> bookingOccupants = new ArrayList<String>();

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
    while (venueIndex < numOfVenues) {
      MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
      venueIndex++;
    }
    venueIndex = 0;
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
    while (venueIndex < numOfVenues) {
      if (venueCode.compareTo(venueCodes.get(venueIndex)) == 0) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueNames.get(venueIndex));
        venueIndex = 0;
        return;
      }
      venueIndex++;
    }
    venueIndex = 0;

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

    // Obtain venue
    String venueCode = "";
    int index = -1;
    while (venueIndex < numOfVenues) {
      if (options[0].compareTo(venueCodes.get(venueIndex)) == 0) {
        venueCode = options[0];
        index = venueIndex;
        break;
      }
      venueIndex++;
    }
    venueIndex = 0;

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

    // Confirm booking to user
    String bookRef = BookingReferenceGenerator.generateBookingReference();
    bookingCode.add(bookRef);
    bookingVenue.add(options[0]);
    bookingDate.add(options[1]);
    bookingEmail.add(options[2]);
    bookingOccupants.add(options[3]);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookRef, venueNames.get(index), options[1], options[3]);

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
