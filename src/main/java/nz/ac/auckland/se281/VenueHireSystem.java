package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  ArrayList<String> venueNames = new ArrayList<String>();
  ArrayList<String> venueCodes = new ArrayList<String>();
  ArrayList<String> capacities = new ArrayList<String>();
  ArrayList<String> hireFees = new ArrayList<String>();

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method

    int numOfVenues = hireFees.size();
    int venueIndex = 0;

    // Checks if there are venues
    if (numOfVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
    }

    // Print 
    if (numOfVenues < 10) {
      switch (numOfVenues) {
        case 1:
          MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 2:
          MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 3:
          MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 4:
          MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 5:
          MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 6:
          MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 7:
          MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 8:
          MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        case 9:
          MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
          while (venueIndex < numOfVenues) {
            MessageCli.VENUE_ENTRY.printMessage(venueNames.get(venueIndex), venueCodes.get(venueIndex), capacities.get(venueIndex), hireFees.get(venueIndex), "TODO");
            venueIndex++;
          }
          venueIndex = 0;
          break;
        default:
          break;
      }
    }

    // If number of venues 10 or greater

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
    capacities.add(capacityInput);
    hireFees.add(hireFeeInput);
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
