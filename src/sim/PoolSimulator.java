package sim;

/**
 * An interface to simulate the movements of ball on a pool.
 */
public interface PoolSimulator {

  /**
   * Start the simulation with a ball at the given position, with the given radius and velocity.
   * This method throws an exception if its parameters are invalid.
   * The conditions for invalidity are dependent on the implementation.
   *
   * @param x      x coordinate of the ball
   * @param y      y coordinate of the ball
   * @param radius radius of the ball
   * @param speed  initial speed of the ball
   * @param dx     unit horizontal direction of movement
   * @param dy     unit vertical direction of movement
   * @throws IllegalArgumentException if inputs are invalid
   */
  void start(int x, int y, int radius, int speed, double dx, double dy) throws IllegalArgumentException;


  /**
   * Advances the simulation by one discrete step (to the next bounce, or the ball stopping)
   */
  void advance();

  /**
   * Gets the width of the table.
   *
   * @return int
   */
  int getTableWidth();

  /**
   * Gets the height of the table.
   *
   * @return int
   */
  int getTableHeight();

  /**
   * Gets the x coordinate of the current position of the ball.
   *
   * @return double
   */
  double getBallPositionX();

  /**
   * Gets the y coordinate of the current position of the ball.
   *
   * @return double
   */
  double getBallPositionY();

  /**
   * Gets the radius of the ball.
   *
   * @return double
   */
  double getBallRadius();

  /**
   * Gets the x component of the current velocity of the ball.
   *
   * @return double
   */
  double getBallVelocityX();

  /**
   * Gets the y component of the current velocity of the ball.
   *
   * @return double
   */
  double getBallVelocityY();

  /**
   * Gets the status of the simulation at the current step.
   *
   * @return String
   */
  String getStatus();

}
