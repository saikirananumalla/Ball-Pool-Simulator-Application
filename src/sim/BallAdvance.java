package sim;

import sim.entity.Ball;
import sim.entity.Table;

public interface BallAdvance {

  /**
   * Advance the ball to next position on the table.
   *
   * @param ball  Ball
   * @param table Table
   * @return status of the ball based on the simulation status
   */
  String advance(Ball ball, Table table);

}
