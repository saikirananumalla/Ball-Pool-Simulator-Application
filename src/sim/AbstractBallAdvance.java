package sim;

import sim.entity.Ball;
import sim.entity.Table;

import static sim.util.Constants.STATUS_BOTTOM_EDGE;
import static sim.util.Constants.STATUS_LEFT_EDGE;
import static sim.util.Constants.STATUS_RIGHT_EDGE;
import static sim.util.Constants.STATUS_STATIONARY;
import static sim.util.Constants.STATUS_TOP_EDGE;

/**
 * A class to abstract the advance the ball to next position.
 */
public abstract class AbstractBallAdvance implements BallAdvance {

  @Override
  public String advance(Ball ball, Table table) {

    if (ball.getSpeed() <= 0) {
      return STATUS_STATIONARY;
    }

    String status;

    double xMax = table.getXMax(), yMax = table.getYMax();

    double dx = ball.getDx(), dy = ball.getDy();

    if (dx == 0 && dy == 0){
      return STATUS_STATIONARY;
    }

    double x = ball.getPositionX(), y = ball.getPositionY(), r = ball.getRadius(), s = ball.getSpeed();

    double txr = Double.POSITIVE_INFINITY, txl = Double.POSITIVE_INFINITY, tx, tyt = Double.POSITIVE_INFINITY, tyb = Double.POSITIVE_INFINITY, ty, tStop, minT;

    double adx = Math.abs(dx), ady = Math.abs(dy);

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
      status = STATUS_STATIONARY;
    } else {
      if (tx < ty) {
        minT = tx;
        if (txl > txr) {
          status = STATUS_RIGHT_EDGE;
        } else {
          status = STATUS_LEFT_EDGE;
        }
      } else {
        minT = ty;
        if (tyb > tyt) {
          status = STATUS_TOP_EDGE;
        } else {
          status = STATUS_BOTTOM_EDGE;
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

    return status;
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
  protected abstract double getEdgeCollisionTime(double speed, double unitDirection
          , double pOld, double pNew);

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
