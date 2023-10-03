package sim;

/**
 * An implementation of BallAdvance following newtonian physics.
 */
public class FrictionBallAdvance extends AbstractBallAdvance {

  /**
   * Coefficient of friction multiplied by the gravity.
   */
  private static final double acc = 0.981;

  @Override
  protected double getEdgeCollisionTime(double speed, double unitDirection,
                                        double pOld, double pNew) {
    return getRoot((acc * unitDirection) / 2, (-speed * unitDirection), (pNew - pOld));
  }

  @Override
  protected double getStopTime(double speed) {
    return speed / acc;
  }

  @Override
  protected double getDisplacement(double speed, double unitDirection, double time) {
    return speed * unitDirection * time - ((acc * unitDirection) / 2 * time * time);
  }

  @Override
  protected double getSpeedDampFactor(double time) {
    return acc * time;
  }

  private double getRoot(double a, double b, double c) {
    double delta = b * b - 4 * a * c;

    if (delta < 0) {
      return Double.POSITIVE_INFINITY;
    }

    double t1 = 2 * c / (-b - Math.sqrt(delta));
    double t2 = 2 * c / (-b + Math.sqrt(delta));

    if (t1 < 0 && t2 < 0) {
      return Double.POSITIVE_INFINITY;
    } else if (t2 >= 0 && t1 >= 0) {
      return Math.min(t1, t2);
    } else if (t2 >= 0) {
      return t2;
    } else if (t1 >= 0) {
      return t1;
    }

    return Math.min(t1, t2);
  }
}
