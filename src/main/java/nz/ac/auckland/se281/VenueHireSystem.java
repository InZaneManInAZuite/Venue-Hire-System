package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VenueHireSystem {

  // Store all available venue information
  private ArrayList<String> venueNames = new ArrayList<String>();
  private ArrayList<String> venueCodes = new ArrayList<String>();
  private ArrayList<String> capacities = new ArrayList<String>();
  private ArrayList<String> hireFees = new ArrayList<String>();
  private int numOfVenues = 0;
  
  // Store all booking information
  private ArrayList<String> bookingRefs = new ArrayList<String>();
  private ArrayList<String> bookingCodes = new ArrayList<String>();
  private ArrayList<String> bookingDates = new ArrayList<String>();
  private ArrayList<String> bookingEmails = new ArrayList<String>();
  private ArrayList<String> bookingAttendees = new ArrayList<String>();
  private int numOfBookings = 0;

  // Store all service information
  private ArrayList<String> serviceRefs = new ArrayList<String>();
  private ArrayList<String> serviceNames = new ArrayList<String>();
  private ArrayList<Integer> serviceCosts = new ArrayList<Integer>();
  private int numOfServices = 0;
  
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

      // Obtain current date (system date or computer date if system date is not set)
      Calendar earliestDate = new GregorianCalendar();
      if (systemDate.isEmpty()) {
        earliestDate = Calendar.getInstance();
      } else {
        String[] systemDateSplit = systemDate.split("/");
        earliestDate.set(Calendar.YEAR, Integer.parseInt(systemDateSplit[2]));
        earliestDate.set(Calendar.MONTH, Integer.parseInt(systemDateSplit[1]));
        earliestDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(systemDateSplit[0]));
      }
      
      // Check which dates the venue is booked
      ArrayList<String> venueDates = new ArrayList<String>();
      int venueUsed = 0;
      for (int j = 0; j < numOfBookings; j++) {
        if (venueCodes.get(i).equals(bookingCodes.get(j))) {
          venueDates.add(bookingDates.get(j));
          venueUsed++;
        }
      }

      // Initiates the process of finding the earliest available date
      String dayString = Integer.toString(earliestDate.get(Calendar.DAY_OF_MONTH));
      String monthString = Integer.toString(earliestDate.get(Calendar.MONTH));
      String yearString = Integer.toString(earliestDate.get(Calendar.YEAR));
      if (Integer.parseInt(dayString) < 10) {
        dayString = "0" + dayString;} 
      if (Integer.parseInt(monthString) < 10) {
        monthString = "0" + monthString;}
      String earliestString = dayString + "/" + monthString + "/" + yearString;
      boolean dateFound = false;
      if (venueUsed == 0) {
        dateFound = true;
      }

      // Checks each booked date to find the earliest available date
      while (dateFound == false) {

        // Check if the potential earliest date is already booked
        for (int k = 0; k < venueUsed; k++) {
          if (earliestString.equals(venueDates.get(k))){
            break;
          }
          if (k == venueUsed - 1) {
            dateFound = true;
            break;
          }
        }

        // If the potential earliest date is already booked, move to the next day
        if (dateFound == false) {
          earliestDate.add(Calendar.DAY_OF_MONTH, 1);
          dayString = Integer.toString(earliestDate.get(Calendar.DAY_OF_MONTH));
          monthString = Integer.toString(earliestDate.get(Calendar.MONTH));
          yearString = Integer.toString(earliestDate.get(Calendar.YEAR));
          if (Integer.parseInt(dayString) < 10) {
            dayString = "0" + dayString;} 
          if (Integer.parseInt(monthString) < 10) {
            monthString = "0" + monthString;}
          earliestString = dayString + "/" + monthString + "/" + yearString;
        }
      }
  
      MessageCli.VENUE_ENTRY.printMessage(venueNames.get(i), venueCodes.get(i), capacities.get(i), hireFees.get(i), earliestString);
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

    // Obtain booking details
    String bookingCode = options[0];
    String bookingDate = options[1];
    String bookingEmail = options[2];
    String bookingAttendee = options[3];



    // Obtain booking's venue details
    String venueCode = "";
    String venueName = "";
    int venueCapacity = 0;
    for (int i = 0; i < numOfVenues; i++) {
      if (bookingCode.equals(venueCodes.get(i))) {
        venueCode = bookingCode;
        venueName = venueNames.get(i);
        venueCapacity = Integer.parseInt(capacities.get(i));
        break;
      }
    }
    


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
      if (venueCode.equals(bookingCodes.get(j)) && bookingDate.equals(bookingDates.get(j))){
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, bookingDate);
        return;
      }
    }
    Calendar systemDateCal = new GregorianCalendar();
    Calendar bookingDateCal = new GregorianCalendar();
    String[] currentDateSplit = systemDate.split("/");
    systemDateCal.set(Integer.parseInt(currentDateSplit[2]), Integer.parseInt(currentDateSplit[1]), Integer.parseInt(currentDateSplit[0]));
    String[] bookingDateSplit = bookingDate.split("/");
    bookingDateCal.set(Integer.parseInt(bookingDateSplit[2]), Integer.parseInt(bookingDateSplit[1]), Integer.parseInt(bookingDateSplit[0]));
    if (bookingDateCal.before(systemDateCal)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
      return;
    }




    // Adjust booking attendee number if attendee is too large or too small
    int bookingToBeOccupied = Integer.parseInt(bookingAttendee);
    if (venueCapacity < bookingToBeOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], Integer.toString(venueCapacity), Integer.toString(venueCapacity));
      bookingToBeOccupied = venueCapacity;
    }
    int oneForth = (int)(venueCapacity * 0.25);
    if (oneForth > bookingToBeOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], Integer.toString(oneForth), Integer.toString(venueCapacity));
      bookingToBeOccupied = oneForth;
    }
    bookingAttendee = Integer.toString(bookingToBeOccupied);



    // Make and confirm booking to user
    String bookingRef = BookingReferenceGenerator.generateBookingReference();
    bookingRefs.add(bookingRef);
    bookingCodes.add(bookingCode);
    bookingDates.add(bookingDate);
    bookingEmails.add(bookingEmail);
    bookingAttendees.add(bookingAttendee);
    numOfBookings++;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingRef, venueName, bookingDate, bookingAttendee);
  }

  public void printBookings(String venueCode) {
    // TODO implement this method

    // Find the venue name
    boolean venueFound = false;
    String venueName = "";
    for (int i = 0; i < numOfVenues; i++) {
      if (venueCode.equals(venueCodes.get(i))) {
        venueName = venueNames.get(i);
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
        venueFound = true;
      }
    }
    if (venueFound == false) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // Check which booking references and dates the venue is booked for
    ArrayList<String> venueDates = new ArrayList<String>();
    ArrayList<String> venueRefs = new ArrayList<String>();
    int venueUsed = 0;
    for (int j = 0; j < numOfBookings; j++) {
      if (venueCode.equals(bookingCodes.get(j))) {
        venueDates.add(bookingDates.get(j));
        venueRefs.add(bookingRefs.get(j));
        venueUsed++;
      }
    }



    // If the venue is not booked, print message
    if (venueUsed == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
      return;
    }



    // Print all the bookings for the venue
    for (int k = 0; k < venueUsed; k++) {
      MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(venueRefs.get(k), venueDates.get(k));
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
    
    // Check if booking reference exists
    boolean bookingExists = false;
    String bookingAttendee = "";
    for (int j = 0; j < numOfBookings; j++) {
      if (bookingReference.equals(bookingRefs.get(j))) {
        bookingAttendee = bookingAttendees.get(j);
        bookingExists = true;
        break;
      }
    }
    if (!bookingExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }

    // Complete the service order
    serviceRefs.add(bookingReference);
    serviceNames.add(cateringType.getName());
    serviceCosts.add(Integer.parseInt(bookingAttendee) * cateringType.getCostPerPerson());
    numOfServices++;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + cateringType.getName() + ")", bookingReference);


  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookingExists = false;
    for (int j = 0; j < numOfBookings; j++) {
      if (bookingReference.equals(bookingRefs.get(j))) {
        bookingExists = true;
        break;
      }
    }
    if (!bookingExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }

    // Add music service to record
    serviceCosts.add(500);
    serviceNames.add("Music");
    serviceRefs.add(bookingReference);
    numOfServices++;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookingExists = false;
    for (int j = 0; j < numOfBookings; j++) {
      if (bookingReference.equals(bookingRefs.get(j))) {
        bookingExists = true;
        break;
      }
    }
    if (!bookingExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }

    // Add music service to record
    if (floralType == Types.FloralType.DELUXE) {
      serviceCosts.add(1000);
    } else {
      serviceCosts.add(550);
    }
    serviceNames.add(floralType.getName());
    serviceRefs.add(bookingReference);
    numOfServices++;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + floralType.getName() + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method

    
  }
}
