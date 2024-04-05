package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // Store all important details of the venue hire system
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private int numOfVenues = 0;
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

    // If no system date is set, set it to the current date temporarily
    String tempDate = systemDate;
    if (tempDate.isEmpty()) {
      Calendar currentDate = new GregorianCalendar();
      currentDate = Calendar.getInstance();
      tempDate =
          currentDate.get(Calendar.DAY_OF_MONTH)
              + "/"
              + currentDate.get(Calendar.MONTH)
              + "/"
              + currentDate.get(Calendar.YEAR);

      if (currentDate.get(Calendar.DAY_OF_MONTH) < 10) {
        tempDate = "0" + tempDate;
      }
    }

    // Print all the venues and their details
    for (int i = 0; i < numOfVenues; i++) {

      if (venues.get(i).getEarliest() == null) {
        venues.get(i).setEmptyEarliest(tempDate);
      }

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

    // Check if the system date was not set before
    boolean systemWasSet = false;
    if (!systemDate.isEmpty()) {
      systemWasSet = true;
    }

    // Split the date into day, month and year
    String[] dateSplit = dateInput.split("/");

    // Format day and month if they are less than 10
    String day = dateSplit[0];
    if (Integer.parseInt(dateSplit[0]) < 10) {
      day = "0" + Integer.parseInt(dateSplit[0]);
    }

    // Format month if it is less than 10
    String month = dateSplit[1];
    if (Integer.parseInt(dateSplit[1]) < 10) {
      month = "0" + Integer.parseInt(dateSplit[1]);
    }

    // Set the system date
    systemDate = day + "/" + month + "/" + dateSplit[2];

    // Update the earliest available date for all venues
    if (systemWasSet) {
      for (int i = 0; i < numOfVenues; i++) {
        venues.get(i).updateEarliest(systemDate);
      }
    } else {
      for (int i = 0; i < numOfVenues; i++) {
        venues.get(i).setEmptyEarliest(systemDate);
      }
    }

    // Print the system date
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
    String bookCode = options[0];
    String bookCheckIn = options[1];
    String bookEmail = options[2];
    String bookAttendee = options[3];

    // Checks if system date is set
    if (systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // Checks if there are venues
    if (numOfVenues == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // Check if booking date is in the past
    // Obtain the system date and booking date as Calendar objects
    String[] systemSplit = systemDate.split("/");
    String[] bookingSplit = bookCheckIn.split("/");
    Calendar systemCal = new GregorianCalendar();
    Calendar bookingCal = new GregorianCalendar();
    systemCal.set(
        Integer.parseInt(systemSplit[2]),
        Integer.parseInt(systemSplit[1]),
        Integer.parseInt(systemSplit[0]));
    bookingCal.set(
        Integer.parseInt(bookingSplit[2]),
        Integer.parseInt(bookingSplit[1]),
        Integer.parseInt(bookingSplit[0]));

    // Compare the two dates
    if (bookingCal.before(systemCal)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookCheckIn, systemDate);
      return;
    }

    // Adjust the booking date format if it is less than 10
    String day = bookingSplit[0];
    if (Integer.parseInt(bookingSplit[0]) < 10) {
      day = "0" + Integer.parseInt(bookingSplit[0]);
    }

    // Adjust the booking month format if it is less than 10
    String month = bookingSplit[1];
    if (Integer.parseInt(bookingSplit[1]) < 10) {
      month = "0" + Integer.parseInt(bookingSplit[1]);
    }

    // Set the booking date
    bookCheckIn = day + "/" + month + "/" + bookingSplit[2];

    // Obtain booking's venue details
    String venueCode = "";
    String venueName = "";
    int venueCapacity = 0;
    int venueIndex;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      if (bookCode.equals(venues.get(venueIndex).getCode())) {

        // Check if the venue is already booked
        if (venues.get(venueIndex).isDateBooked(bookCheckIn)) {
          MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
              venues.get(venueIndex).getName(), bookCheckIn);
          return;
        }

        // else, set the venue details
        venueCode = bookCode;
        venueName = venues.get(venueIndex).getName();
        venueCapacity = Integer.parseInt(venues.get(venueIndex).getCapacity());
        break;
      }
    }

    // Check if the venue code is not found
    if (venueCode.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage();
    }

    // Adjust booking attendee number if attendee is too large or too small
    // Check if the number of attendees is too large
    int attendeeInt = Integer.parseInt(bookAttendee);
    if (venueCapacity < attendeeInt) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], Integer.toString(venueCapacity), Integer.toString(venueCapacity));
      attendeeInt = venueCapacity;
    }

    // Check if the number of attendees is too small
    int oneForth = (int) (venueCapacity * 0.25);
    if (oneForth > attendeeInt) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], Integer.toString(oneForth), Integer.toString(venueCapacity));
      attendeeInt = oneForth;
    }
    bookAttendee = Integer.toString(attendeeInt);

    // Make and confirm booking to user
    String bookingRef = BookingReferenceGenerator.generateBookingReference();
    Booking booking =
        new Booking(bookingRef, bookCheckIn, bookEmail, venueCode, bookAttendee, systemDate);
    venues.get(venueIndex).addBooking(booking);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingRef, venueName, bookCheckIn, bookAttendee);
  }

  public void printBookings(String venueCode) {
    // TODO implement this method

    // Check if the venue exits
    boolean venueFound = false;
    String venueName = "";
    int venueIndex = 0;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      if (venueCode.equals(venues.get(venueIndex).getCode())) {
        venueName = venues.get(venueIndex).getName();
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
        venueFound = true;
        break;
      }
    }

    // If the venue is not found, print message
    if (venueFound == false) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // If the venue is not booked, print message
    if (venues.get(venueIndex).getNumOfVenueBookings() == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
      return;
    }

    // Print all the bookings for the venue
    for (int i = 0; i < venues.get(venueIndex).getNumOfVenueBookings(); i++) {
      String refs = venues.get(venueIndex).getBooking(i).getRef();
      String checkIn = venues.get(venueIndex).getBooking(i).getCheckIn();
      MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(refs, checkIn);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookExists = false;
    String bookAttendee = "";
    int venueIndex = 0;
    int bookIndex = 0;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      for (bookIndex = 0; bookIndex < venues.get(venueIndex).getNumOfVenueBookings(); bookIndex++) {
        if (bookingReference.equals(venues.get(venueIndex).getBooking(bookIndex).getRef())) {
          bookAttendee = venues.get(venueIndex).getBooking(bookIndex).getAttendee();
          bookExists = true;
          break;
        }
      }
      if (bookExists) {
        break;
      }
    }

    // If booking reference does not exist, print message
    if (!bookExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }

    // Complete the service order
    int cost = cateringType.getCostPerPerson() * Integer.parseInt(bookAttendee);
    String type = cateringType.getName();
    Catering catering = new Catering(type, cost);
    venues.get(venueIndex).getBooking(bookIndex).addService(catering);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + type + ")", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookExists = false;
    int venueIndex = 0;
    int bookIndex = 0;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      for (bookIndex = 0; bookIndex < venues.get(venueIndex).getNumOfVenueBookings(); bookIndex++) {
        if (bookingReference.equals(venues.get(venueIndex).getBooking(bookIndex).getRef())) {
          bookExists = true;
          break;
        }
      }
      if (bookExists) {
        break;
      }
    }

    // If booking reference does not exist, print message
    if (!bookExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }

    // Add music service to
    venues.get(venueIndex).getBooking(bookIndex).addService(new Music());
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookingExists = false;
    int venueIndex = 0;
    int bookIndex = 0;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      for (bookIndex = 0; bookIndex < venues.get(venueIndex).getNumOfVenueBookings(); bookIndex++) {
        if (bookingReference.equals(venues.get(venueIndex).getBooking(bookIndex).getRef())) {
          bookingExists = true;
          break;
        }
      }
      if (bookingExists) {
        break;
      }
    }

    // If booking reference does not exist, print message
    if (!bookingExists) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }

    // Add floral service to booking
    String type = floralType.getName();
    Flowers floral = new Flowers(type);
    floral.setCost();
    venues.get(venueIndex).getBooking(bookIndex).addService(floral);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + floralType.getName() + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method

    // Check if booking reference exists
    boolean bookExists = false;
    int venueIndex = 0;
    int bookIndex = 0;
    for (venueIndex = 0; venueIndex < numOfVenues; venueIndex++) {
      for (bookIndex = 0; bookIndex < venues.get(venueIndex).getNumOfVenueBookings(); bookIndex++) {
        if (bookingReference.equals(venues.get(venueIndex).getBooking(bookIndex).getRef())) {
          bookExists = true;
          break;
        }
      }
      if (bookExists) {
        break;
      }
    }

    // If booking reference does not exist, print message
    if (!bookExists) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // Print first half of the invoice
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        venues.get(venueIndex).getBooking(bookIndex).getEmail(),
        venues.get(venueIndex).getBooking(bookIndex).getBookDate(),
        venues.get(venueIndex).getBooking(bookIndex).getCheckIn(),
        venues.get(venueIndex).getBooking(bookIndex).getAttendee(),
        venues.get(venueIndex).getName());

    // Obtain details for the middle body of the invoice
    // Get hire cost to be paid
    String hireCost = venues.get(venueIndex).getHireFee();
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(hireCost);
    int sum = Integer.parseInt(hireCost);

    // Get the cost of all services to be paid
    for (int i = 0; i < venues.get(venueIndex).getBooking(bookIndex).getNumOfServices(); i++) {
      String serve = venues.get(venueIndex).getBooking(bookIndex).getServices().get(i).serve;

      // Check if the service is catering, music or floral
      if (serve.equals("Catering")) {
        Catering catering =
            (Catering) venues.get(venueIndex).getBooking(bookIndex).getServices().get(i);
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            catering.getType(), Integer.toString(catering.cost));
        sum += catering.cost;
      }

      // Check if the service is music
      else if (serve.equals("Music")) {
        Music music = (Music) venues.get(venueIndex).getBooking(bookIndex).getServices().get(i);
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(Integer.toString(music.getCost()));
        sum += music.getCost();
      }

      // Check if the service is floral
      else {
        Flowers floral =
            (Flowers) venues.get(venueIndex).getBooking(bookIndex).getServices().get(i);
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            floral.getType(), Integer.toString(floral.getCost()));
        sum += floral.getCost();
      }
    }

    // Print bottom half of the invoice
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(sum));
  }
}
