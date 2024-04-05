package nz.ac.auckland.se281;

public abstract class Service {

  // Find important details of each instance of a service
  protected int cost;
  protected String serve;

  // Constructors for the service
  protected Service() {}

  // Obtain the details of the service without the ability to modify them
  public int getCost() {
    return this.cost;
  }
}
