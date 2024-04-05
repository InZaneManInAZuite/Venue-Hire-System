package nz.ac.auckland.se281;

public class Catering extends Service {
  private String type;

  public Catering(String type, int cost) {
    this.serve = "Catering";
    this.type = type;
    this.cost = cost;
  }

  public String getType() {
    return this.type;
  }
}
