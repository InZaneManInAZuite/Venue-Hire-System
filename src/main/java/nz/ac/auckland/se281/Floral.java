package nz.ac.auckland.se281;

public class Floral extends Service {
  private String type;

  public Floral(String type) {
    this.serve = "Floral";
    this.type = type;
  }

  public void setCost() {
    if (this.type.equals("DELUXE")) {
      this.cost = 1000;
    } else {
      this.cost = 550;
    }
  }

  public String getType() {
    return this.type;
  }
}
