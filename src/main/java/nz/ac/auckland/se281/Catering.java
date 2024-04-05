package nz.ac.auckland.se281;

public class Catering extends Service {

  // Find important details of each instance of a catering service
  private String type;

  // Constructors for the catering service
  public Catering(String type, int cost) {
    this.serve = "Catering";
    this.type = type;
    this.cost = cost;
  }

  // Obtain the details of the catering service without the ability to modify them
  public String getType() {
    return this.type;
  }
}
