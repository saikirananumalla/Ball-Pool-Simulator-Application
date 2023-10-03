package sim;

import sim.entity.Ball;
import sim.entity.BallStatus;
import sim.entity.Table;

/**
 * A class to abstract the advance the ball to next position.
 */
public abstract class AbstractBallAdvance implements BallAdvance {

  @Override
  public void advance(Ball ball, Table table) {

    if (ball.getSpeed() <= 0) {
      ball.setStatus(BallStatus.STATUS_STATIONARY);
      return;
    }

    double xMax = table.getXMax();
    double yMax = table.getYMax();

    double dx = ball.getDx();
    double dy = ball.getDy();

    if (dx == 0 && dy == 0) {
      ball.setStatus(BallStatus.STATUS_STATIONARY);
      return;
    }

    double x = ball.getPositionX();
    double y = ball.getPositionY();
    double r = ball.getRadius();
    double s = ball.getSpeed();

    double txr = Double.POSITIVE_INFINITY;
    double txl = Double.POSITIVE_INFINITY;

    double tx;
    double tyt = Double.POSITIVE_INFINITY;
    double tyb = Double.POSITIVE_INFINITY;
    double ty;
    double tStop;
    double minT;

    double adx = Math.abs(dx);
    double ady = Math.abs(dy);

    if (dx > 0) {
      txr = getEdgeCollisionTime(s, adx, x, xMax - r);
    } else if (dx < 0) {
      txl = getEdgeCollisionTime(s, adx, r, x);
    }

    tx = Math.min(txr, txl);

    if (dy > 0) {
      tyt = getEdgeCollisionTime(s, ady, y, yMax - r);
    } else if (dy < 0) {
      tyb = getEdgeCollisionTime(s, ady, r, y);
    }

    ty = Math.min(tyt, tyb);

    tStop = getStopTime(s);

    if (tStop < tx && tStop < ty) {
      minT = tStop;
      ball.setStatus(BallStatus.STATUS_STATIONARY);
    } else {
      if (tx < ty) {
        minT = tx;
        if (txl > txr) {
          ball.setStatus(BallStatus.STATUS_RIGHT_EDGE);
        } else {
          ball.setStatus(BallStatus.STATUS_LEFT_EDGE);
        }
      } else {
        minT = ty;
        if (tyb > tyt) {
          ball.setStatus(BallStatus.STATUS_TOP_EDGE);
        } else {
          ball.setStatus(BallStatus.STATUS_BOTTOM_EDGE);
        }
      }
    }

    ball.advanceX(getDisplacement(s, dx, minT));
    ball.advanceY(getDisplacement(s, dy, minT));

    if (minT == tx) {
      ball.invertDx();
    } else if (minT == ty) {
      ball.invertDy();
    }

    ball.setSpeed(s - getSpeedDampFactor(minT));

    if (ball.getSpeed() <= 0) {
      ball.setStatus(BallStatus.STATUS_STATIONARY);
    }
  }

  /**
   * Gets the time for collision for the given edge.
   *
   * @param speed         speed of the ball
   * @param unitDirection direction of movement along a given axis
   * @param pOld          initial point
   * @param pNew          final point
   * @return time
   */
  protected abstract double getEdgeCollisionTime(double speed, double unitDirection,
                                                 double pOld, double pNew);

  /**
   * Gets the time for ball to stop on the table.
   *
   * @param speed speed of the ball
   * @return time
   */
  protected abstract double getStopTime(double speed);

  /**
   * Gets the displacement of the ball.
   *
   * @param speed         speed of the ball
   * @param unitDirection direction of movement along a given axis
   * @param time          total displacement time
   * @return displacement
   */
  protected abstract double getDisplacement(double speed, double unitDirection, double time);

  /**
   * Gets the dampening factor for speed.
   *
   * @param time time of travel
   * @return dampening factor
   */
  protected abstract double getSpeedDampFactor(double time);


}
