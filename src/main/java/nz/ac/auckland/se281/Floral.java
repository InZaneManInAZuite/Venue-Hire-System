package nz.ac.auckland.se281;

public class Floral extends Service {

  // Find important details of each instance of a floral service
  private String type;

  // Constructors for the floral service
  public Floral(String type) {
    this.serve = "Floral";
    this.type = type;
  }

  // Obtain the details of the floral service without the ability to modify them
  public String getType() {
    return this.type;
  }

  // Set the cost of the floral service
  public void setCost() {
    if (this.type.equals("DELUXE")) {
      this.cost = 1000;
    } else {
      this.cost = 550;
    }
  }
}
