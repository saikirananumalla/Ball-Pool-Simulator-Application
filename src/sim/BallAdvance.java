package sim;

import sim.entity.Ball;
import sim.entity.Table;

/**
 * An interface to implement the advancing of the ball on a given table.
 */
public interface BallAdvance {

  /**
   * Advance the ball to next position on the table.
   *
   * @param ball  Ball
   * @param table Table
   */
  void advance(Ball ball, Table table);

}
