package sim.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static sim.util.Constants.STATUS_LEFT_EDGE;

/**
 * A JUnit class to test the ball status class.
 */
public class BallStatusTest {

  @Test
  public void asString() {
    BallStatus ballStatus = BallStatus.STATUS_LEFT_EDGE;
    assertEquals(STATUS_LEFT_EDGE, ballStatus.asString());
  }
}