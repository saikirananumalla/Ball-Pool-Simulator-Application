package sim.util;

/**
 * A class to hold all constant values used in the project at a single place.
 */
public abstract class Constants {

  /**
   * Private constructor ensures the object won't be initialized.
   */
  private Constants() {
  }

  public static final String SIMPLE = "simple";

  public static final String FRICTION = "friction";

  public static final String INVALID_INPUT = "Invalid Input";

  public static final String STATUS_NOT_SET_UP = "Ball not set up";

  public static final String STATUS_SIMULATION_STARTED = "Simulation started";

  public static final String STATUS_BOTTOM_EDGE = "Ball hit bottom edge";

  public static final String STATUS_TOP_EDGE = "Ball hit top edge";

  public static final String STATUS_LEFT_EDGE = "Ball hit left edge";

  public static final String STATUS_RIGHT_EDGE = "Ball hit right edge";

  public static final String STATUS_STATIONARY = "Ball is stationary";
}
