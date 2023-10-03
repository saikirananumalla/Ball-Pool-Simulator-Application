package sim.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static sim.util.Constants.STATUS_SIMULATION_STARTED;
import static sim.util.Constants.STATUS_TOP_EDGE;

/**
 * A Junit class to test the Ball class.
 */
public class BallTest {

  private Ball ball;

  @Before
  public void setUp() throws Exception {
    ball = new Ball(100, 100, 20, 0.5, 0.6, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUp_NegativeRadius() {
    new Ball(100, 100, -20, 0.5, 0.6, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUp_NegativeSpeed() {
    new Ball(100, 100, 20, 0.5, 0.6, -15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUp_NegativeX() {
    new Ball(-100, 100, 20, 0.5, 0.6, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUp_NegativeY() {
    new Ball(100, -100, 20, 0.5, 0.6, 15);
  }


  @Test
  public void getPositionX() {
    assertEquals(100, ball.getPositionX(), 0);
  }

  @Test
  public void advanceX() {
    ball.advanceX(20);
    assertEquals(120, ball.getPositionX(), 0);
  }

  @Test
  public void getPositionY() {
    assertEquals(100, ball.getPositionY(), 0);
  }

  @Test
  public void advanceY() {
    ball.advanceY(10.5);
    assertEquals(110.5, ball.getPositionY(), 0);
  }

  @Test
  public void getRadius() {
    assertEquals(20, ball.getRadius(), 0);
  }

  @Test
  public void getDx() {
    assertEquals(0.6401843996644799, ball.getDx(), 0);
  }

  @Test
  public void invertDx() {
    ball.invertDx();
    assertEquals(-0.6401843996644799, ball.getDx(), 0);
  }

  @Test
  public void getDy() {
    assertEquals(0.7682212795973759, ball.getDy(), 0);
  }

  @Test
  public void invertDy() {
    ball.invertDy();
    assertEquals(-0.7682212795973759, ball.getDy(), 0);
  }

  @Test
  public void getSpeed() {
    assertEquals(15, ball.getSpeed(), 0);
  }

  @Test
  public void setSpeed() {
    ball.setSpeed(2);
    assertEquals(2, ball.getSpeed(), 0);
  }

  @Test
  public void setSpeed_negative() {
    ball.setSpeed(-1);
    assertEquals(0, ball.getSpeed(), 0);
  }


  @Test
  public void getVx() {
    assertEquals(9.602765994967198, ball.getVx(), 0);
  }

  @Test
  public void getVy() {
    assertEquals(11.523319193960639, ball.getVy(), 0);
  }

  @Test
  public void getStatus() {
    assertEquals(STATUS_SIMULATION_STARTED, ball.getStatus());
  }

  @Test
  public void setStatus() {
    ball.setStatus(BallStatus.STATUS_TOP_EDGE);
    assertEquals(STATUS_TOP_EDGE, ball.getStatus());
  }
}