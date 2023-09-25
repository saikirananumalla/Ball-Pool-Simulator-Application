package sim;

import java.util.List;

import sim.entity.Ball;
import sim.entity.Table;

import static sim.util.Constants.FRICTION;
import static sim.util.Constants.INVALID_INPUT;
import static sim.util.Constants.SIMPLE;
import static sim.util.Constants.STATUS_NOT_SET_UP;
import static sim.util.Constants.STATUS_SIMULATION_STARTED;
import static sim.util.Constants.STATUS_STATIONARY;

/**
 * A class to implement the PoolSimulator interface.
 */
public class SimplePoolSimulator implements PoolSimulator {
  private String status;

  private final String type;

  private Ball ball;

  private final Table table;

  /**
   * Initializes a simple pool simulator.
   *
   * @param width  width of the table
   * @param height height of the table
   * @param type   type of table
   * @throws IllegalArgumentException if width or height is non-positive or type is an unsupported
   */
  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {
    if (width <= 0 || height <= 0 || !List.of(SIMPLE, FRICTION).contains(type)) {
      throw new IllegalArgumentException(INVALID_INPUT);
    }

    table = new Table(width, height);
    this.type = type;
    status = STATUS_NOT_SET_UP;
  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException {

    if (radius < 0 || radius >= getTableHeight() || radius >= getTableWidth() || speed < 0
            || x < radius || y < radius || x > (getTableWidth() - radius)
            || y > (getTableHeight() - radius)) {
      throw new IllegalArgumentException(INVALID_INPUT);
    }

    ball = new Ball(x, y, radius, dx, dy, speed);
    status = STATUS_SIMULATION_STARTED;
  }

  @Override
  public void advance() {
    if (STATUS_STATIONARY.equals(status)) {
      return;
    }

    BallAdvance advance = getBallAdvance(type);
    status = advance.advance(ball, table);
  }

  @Override
  public int getTableWidth() {
    return table.getXMax();
  }

  @Override
  public int getTableHeight() {
    return table.getYMax();
  }

  @Override
  public double getBallPositionX() {
    return ball == null ? Double.NEGATIVE_INFINITY : ball.getPositionX();
  }

  @Override
  public double getBallPositionY() {
    return ball == null ? Double.NEGATIVE_INFINITY : ball.getPositionY();
  }

  @Override
  public double getBallRadius() {
    return ball == null ? Double.NEGATIVE_INFINITY : ball.getRadius();
  }

  @Override
  public double getBallVelocityX() {
    return ball == null ? 0 : ball.getVx();
  }

  @Override
  public double getBallVelocityY() {
    return ball == null ? 0 : ball.getVy();
  }

  @Override
  public String getStatus() {
    return String.format("Status: %s", status);
  }

  private BallAdvance getBallAdvance(String type) {
    if (type.equals(SIMPLE)) {
      return new SimpleBallAdvance();
    }
    return new FrictionBallAdvance();
  }
}
