package sim.entity;

/**
 * An enum to indicate current status of ball on the table.
 */
public enum BallStatus {

  STATUS_NOT_SET_UP("Ball not set up"),

  STATUS_SIMULATION_STARTED("Simulation started"),

  STATUS_BOTTOM_EDGE("Ball hit bottom edge"),

  STATUS_TOP_EDGE("Ball hit top edge"),

  STATUS_LEFT_EDGE("Ball hit left edge"),

  STATUS_RIGHT_EDGE("Ball hit right edge"),

  STATUS_STATIONARY("Ball is stationary");

  private final String status;

  BallStatus(String status) {
    this.status = status;
  }

  public String asString() {
    return status;
  }
}
