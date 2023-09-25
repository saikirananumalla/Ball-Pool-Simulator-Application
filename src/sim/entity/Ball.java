package sim.entity;

import static sim.util.Constants.INVALID_INPUT;

/**
 * A class to represent a ball with its position, direction of movement, radius and speed.
 */
public class Ball {

  private final Vector position;

  private final Vector d;

  private final double radius;

  private double speed;

  /**
   * Create a ball object with normalized direction vector.
   *
   * @param x      x coordinate of start position of ball
   * @param y      y coordinate of start position of ball
   * @param radius radius of the ball
   * @param dx     unit x direction of movement
   * @param dy     unit y direction of movement
   * @param speed  speed of the ball
   * @throws IllegalArgumentException if any values are invalid
   */
  public Ball(int x, int y, int radius, double dx, double dy, int speed) throws IllegalArgumentException {

    if (radius < 0 || speed < 0 || x < 0 || y < 0) {
      throw new IllegalArgumentException(INVALID_INPUT);
    }

    this.radius = radius;
    this.speed = speed;
    position = new Vector(x, y);
    d = new Vector(dx, dy);
    d.normalize();
  }

  /**
   * Gets X coordinate of current position of the ball.
   *
   * @return x-coordinate
   */
  public double getPositionX() {
    return position.getX();
  }

  /**
   * Add given delta x change to x coordinate.
   *
   * @param dx delta x
   */
  public void advanceX(double dx) {
    position.add(dx, 0);
  }

  /**
   * Gets Y coordinate of current position of the ball.
   *
   * @return y-coordinate
   */
  public double getPositionY() {
    return position.getY();
  }

  /**
   * Add given delta y change to y coordinate.
   *
   * @param dy delta y
   */
  public void advanceY(double dy) {
    position.add(0, dy);
  }

  /**
   * Gets the radius.
   *
   * @return radius of the ball
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Gets unit direction of movement in x-axis direction.
   *
   * @return dx
   */
  public double getDx() {
    return d.getX();
  }

  /**
   * Inverts the direction of movement along x-axis.
   */
  public void invertDx() {
    d.invertX();
  }

  /**
   * Gets unit direction of movement in y-axis direction.
   *
   * @return dy
   */
  public double getDy() {
    return d.getY();
  }

  /**
   * Inverts the direction of movement along y-axis.
   */
  public void invertDy() {
    d.invertY();
  }

  /**
   * Gets the speed of the ball.
   *
   * @return the speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Set the speed of the ball to given value, if it is less than zero then set it to zero.
   *
   * @param speed speed of ball
   */
  public void setSpeed(double speed) {
    this.speed = speed;

    if (speed < 0) {
      this.speed = 0;
    }
  }

  /**
   * Gets the velocity of the ball along x-axis.
   *
   * @return Vx
   */
  public double getVx() {
    return getSpeed() * getDx();
  }

  /**
   * Gets the velocity of the ball along y-axis
   *
   * @return Vy
   */
  public double getVy() {
    return getSpeed() * getDy();
  }

}
