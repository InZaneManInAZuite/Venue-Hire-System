package nz.ac.auckland.se281;

public abstract class Service {
  protected int cost;
  protected String serve;

  protected Service() {}

  public int getCost() {
    return this.cost;
  }
}
