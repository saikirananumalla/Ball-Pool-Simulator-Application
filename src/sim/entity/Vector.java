package sim.entity;

/**
 * A class to represent a vector in a 2d plane.
 */
public class Vector {
  private double x;

  private double y;

  /**
   * Initialize a vector with given x and y coordinates.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x coordinate.
   *
   * @return x
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y coordinate.
   *
   * @return y
   */
  public double getY() {
    return y;
  }

  /**
   * Normalize the vector by dividing its coordinate with the rms value.
   */
  public void normalize() {
    if (x == 0 && y == 0){
      return;
    }
    double rms = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    x /= rms;
    y /= rms;
  }

  /**
   * Invert the direction of vector along x-axis.
   */
  public void invertX() {
    x = -x;
  }

  /**
   * Invert the direction of vector along y-axis.
   */
  public void invertY() {
    y = -y;
  }

  /**
   * Move the vector by given delta change.
   *
   * @param dx delta change along x-axis
   * @param dy delta change along y-axis
   */
  public void add(double dx, double dy) {
    x += dx;
    y += dy;
  }

}
