package sim;

/**
 * An implementation of BallAdvance following simple physics.
 */
public class SimpleBallAdvance extends AbstractBallAdvance {

  @Override
  protected double getEdgeCollisionTime(double speed, double unitDirection,
                                        double pOld, double pNew) {
    return (pNew - pOld) / (speed * unitDirection);
  }

  @Override
  protected double getStopTime(double speed) {
    return Double.POSITIVE_INFINITY;
  }

  @Override
  protected double getDisplacement(double speed, double unitDirection, double time) {
    return speed * unitDirection * time;
  }

  @Override
  protected double getSpeedDampFactor(double time) {
    return 5.0;
  }

}
