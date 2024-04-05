package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // Store all available venue information
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private ArrayList<String> venueNames = new ArrayList<String>();
  private ArrayList<String> venueCodes = new ArrayList<String>();
  private ArrayList<String> capacities = new ArrayList<String>();
  private ArrayList<String> hireFees = new ArrayList<String>();
  private int numOfVenues = 0;

  // Store all booking information
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private ArrayList<String> bookingRefs = new ArrayList<String>();
  private ArrayList<String> bookingCodes = new ArrayList<String>();
  private ArrayList<String> bookingDates = new ArrayList<String>();
  private ArrayList<String> bookingEmails = new ArrayList<String>();
  private ArrayList<String> bookingAttendees = new ArrayList<String>();
  private ArrayList<String> bookingMades = new ArrayList<String>();
  private ArrayList<String> bookingNames = new ArrayList<String>();
  private int numOfBookings = 0;

  // Store all service information
  private ArrayList<String> serviceRefs = new ArrayList<String>();
  private ArrayList<String> serviceNames = new ArrayList<String>();
  private ArrayList<Integer> serviceCosts = new ArrayList<Integer>();
  private ArrayList<String> serviceTypes = new ArrayList<String>();
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
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(numOfVenues), "s");
    }

    // Print all the venues and their details
    for (int i = 0; i < numOfVenues; i++) {

      MessageCli.VENUE_ENTRY.printMessage(
          venues.get(i).getName(),
          venues.get(i).getCode(),
          venues.get(i).getCapacity(),
          venues.get(i).getHireFee(),
          venues.get(i).getEarliest());
    }

    return;
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this

    // Initialize variables
    int capacity;
    int hireFee;
    venueName = venueName.trim();

    // Tests if there is a venue name
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Tests if capacity inputted is valid
    try {
      capacity = Integer.parseInt(capacityInput);
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
      hireFee = Integer.parseInt(hireFeeInput);
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
      if (venueCode.equals(venues.get(i).getCode())) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venues.get(i).getName());
        return;
      }
    }

    // Add venues into the system
    Venue venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    venues.add(venue);
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
    for (int j = 0; j < numOfBookings; j++) {
      if (venueCode.equals(bookingCodes.get(j)) && bookingDate.equals(bookingDates.get(j))) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, bookingDate);
        return;
      }
    }
    Calendar systemDateCal = new GregorianCalendar();
    Calendar bookingDateCal = new GregorianCalendar();
    String[] currentDateSplit = systemDate.split("/");
    systemDateCal.set(
        Integer.parseInt(currentDateSplit[2]),
        Integer.parseInt(currentDateSplit[1]),
        Integer.parseInt(currentDateSplit[0]));
    String[] bookingDateSplit = bookingDate.split("/");
    bookingDateCal.set(
        Integer.parseInt(bookingDateSplit[2]),
        Integer.parseInt(bookingDateSplit[1]),
        Integer.parseInt(bookingDateSplit[0]));
    if (bookingDateCal.before(systemDateCal)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
      return;
    }

    // Adjust booking attendee number if attendee is too large or too small
    int bookingToBeOccupied = Integer.parseInt(bookingAttendee);
    if (venueCapacity < bookingToBeOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], Integer.toString(venueCapacity), Integer.toString(venueCapacity));
      bookingToBeOccupied = venueCapacity;
    }
    int oneForth = (int) (venueCapacity * 0.25);
    if (oneForth > bookingToBeOccupied) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], Integer.toString(oneForth), Integer.toString(venueCapacity));
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
    bookingMades.add(systemDate);
    bookingNames.add(venueName);
    numOfBookings++;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingRef, venueName, bookingDate, bookingAttendee);
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
    serviceTypes.add("Catering");
    numOfServices++;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType.getName() + ")", bookingReference);
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
    serviceTypes.add("Music");
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
    serviceTypes.add("Floral");
    numOfServices++;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + floralType.getName() + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookingExists = false;
    String code = "", email = "", made = "", date = "", guests = "", name = "";
    for (int j = 0; j < numOfBookings; j++) {
      if (bookingReference.equals(bookingRefs.get(j))) {
        bookingExists = true;
        code = bookingCodes.get(j);
        email = bookingEmails.get(j);
        made = bookingMades.get(j);
        date = bookingDates.get(j);
        guests = bookingAttendees.get(j);
        name = bookingNames.get(j);
        break;
      }
    }
    if (!bookingExists) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // Print first half of the invoice
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference, email, made, date, guests, name);

    // Obtain details for the middle body of the invoice
    int sum = 0;
    String venueCost;
    for (int i = 0; i < numOfVenues; i++) {
      if (code.equals(venueCodes.get(i))) {
        venueCost = hireFees.get(i);
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueCost);
        sum += Integer.parseInt(venueCost);
        break;
      }
    }
    for (int k = 0; k < numOfServices; k++) {
      if (bookingReference.equals(serviceRefs.get(k))) {
        String cost = Integer.toString(serviceCosts.get(k));
        if (serviceTypes.get(k).equals("Music")) {
          MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(cost);
        } else if (serviceTypes.get(k).equals("Floral")) {
          MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(serviceNames.get(k), cost);
        } else {
          MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(serviceNames.get(k), cost);
        }
        sum += serviceCosts.get(k);
      }
    }

    // Print bottom half of the invoice
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(sum));
  }
}
