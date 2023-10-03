package sim;

import java.util.List;

import sim.entity.Ball;
import sim.entity.BallStatus;
import sim.entity.Table;
import sim.entity.TableType;

import static sim.util.Constants.FRICTION;
import static sim.util.Constants.INVALID_INPUT;
import static sim.util.Constants.SIMPLE;

/**
 * A class to implement the PoolSimulator interface.
 */
public class SimplePoolSimulator implements PoolSimulator {

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

    table = new Table(width, height, type);
  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException {

    if (radius < 0 || radius >= getTableHeight() || radius >= getTableWidth() || speed < 0
            || x < radius || y < radius || x > (getTableWidth() - radius)
            || y > (getTableHeight() - radius) || (speed > 0 && dx == 0 && dy == 0)) {
      throw new IllegalArgumentException(INVALID_INPUT);
    }

    ball = new Ball(x, y, radius, dx, dy, speed);
  }

  @Override
  public void advance() {
    if (ball == null || BallStatus.STATUS_STATIONARY.asString().equals(ball.getStatus())) {
      return;
    }

    BallAdvance advance = getBallAdvance(table.getType());
    advance.advance(ball, table);
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
    return ball == null ? String.format("Status: %s", BallStatus.STATUS_NOT_SET_UP.asString())
            : String.format("Status: %s", ball.getStatus());
  }

  private BallAdvance getBallAdvance(TableType type) {
    if (type.equals(TableType.SIMPLE)) {
      return new SimpleBallAdvance();
    }
    return new FrictionBallAdvance();
  }
}
