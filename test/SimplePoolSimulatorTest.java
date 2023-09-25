import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import sim.PoolSimulator;
import sim.SimplePoolSimulator;

import static org.junit.Assert.*;
import static sim.util.Constants.STATUS_BOTTOM_EDGE;
import static sim.util.Constants.STATUS_LEFT_EDGE;
import static sim.util.Constants.STATUS_NOT_SET_UP;
import static sim.util.Constants.STATUS_RIGHT_EDGE;
import static sim.util.Constants.STATUS_SIMULATION_STARTED;
import static sim.util.Constants.STATUS_STATIONARY;
import static sim.util.Constants.STATUS_TOP_EDGE;

public class SimplePoolSimulatorTest {

  public static final String STATUS = "Status: ";

  PoolSimulator poolSimulatorSimple;

  PoolSimulator poolSimulatorFriction;

  @Before
  public void setUp() {
    poolSimulatorSimple = new SimplePoolSimulator(400, 400, "simple");
    poolSimulatorFriction = new SimplePoolSimulator(400, 400, "friction");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_setUp_InvalidWidth() {
    new SimplePoolSimulator(-20, 40, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_setUp_InvalidHeight() {
    new SimplePoolSimulator(20, -20, "friction");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_setUp_InvalidType() {
    new SimplePoolSimulator(10, 10, "quantum");
  }

  @Test
  public void test_setUp_status() {
    assertEquals(STATUS + STATUS_NOT_SET_UP, poolSimulatorSimple.getStatus());
  }

  @Test
  public void testStart() {
    poolSimulatorSimple.start(50, 50, 10, 2, 1.0, 1.0);
    assertEquals(STATUS + STATUS_SIMULATION_STARTED, poolSimulatorSimple.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRadius() {
    poolSimulatorSimple.start(50, 50, -10, 2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRadiusBiggerThanTable() {
    poolSimulatorSimple.start(50, 50, 401, 2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartPosition() {
    poolSimulatorSimple.start(9, 50, 10, 2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartPositionXMax() {
    poolSimulatorSimple.start(11, 391, 10, 2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeed() {
    poolSimulatorSimple.start(50, 50, 10, -2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPosition() {
    poolSimulatorSimple.start(5, 5, 10, 2, 1.0, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTableBounds() {
    poolSimulatorSimple.start(50, 50, 60, 2, 1.0, 1.0);
  }

  @Test
  public void testSpeedZero() {
    poolSimulatorSimple.start(50, 50, 10, 0, 1.0, 1.0);
    assertEquals(STATUS + STATUS_SIMULATION_STARTED, poolSimulatorSimple.getStatus());
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorSimple.getStatus());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testUnitDirectionZero() {
    poolSimulatorSimple.start(50, 50, 10, 5, 0.0, 0.0);
    assertEquals(STATUS + STATUS_SIMULATION_STARTED, poolSimulatorSimple.getStatus());
  }

  @Test
  public void testSimplePhysicsAdvance() {

    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorSimple.start(100, 100, 20, 60, 1.1, -2);
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorSimple.getStatus());

    assertEquals(List.of(100, 144, 342, 380, 219, 21, 20, 216, 380, 345, 147, 20, 90, 90), ballX);
    assertEquals(List.of(100, 20, 380, 310, 20, 380, 376, 20, 318, 380, 20, 252, 380, 380), ballY);
  }

  @Test
  public void testSimplePhysicsAdvance_Horizontal() {
    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorSimple.start(200, 200, 20, 16, 2.0, 0.0);
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorSimple.getStatus());

    assertEquals(List.of(200, 380, 20, 380, 20), ballX);
    assertEquals(List.of(200, 200, 200, 200, 200), ballY);
  }

  @Test
  public void testSimplePhysicsAdvance_Vertical() {
    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorSimple.start(200, 200, 20, 16, 0.0, 3.0);
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorSimple.getStatus());
    addXY(ballX, ballY, poolSimulatorSimple);
    poolSimulatorSimple.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorSimple.getStatus());

    assertEquals(List.of(200, 380, 20, 380, 20), ballY);
    assertEquals(List.of(200, 200, 200, 200, 200), ballX);
  }

  @Test
  public void testNewtonianPhysicsAdvance() {

    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorFriction.start(100, 100, 20, 60, 1.1, -2);
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorFriction.getStatus());
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorFriction.getStatus());

    assertEquals(List.of(100, 144, 342, 380, 219, 21, 20, 216), ballX);
    assertEquals(List.of(100, 20, 380, 310, 20, 379, 376, 20), ballY);
  }

  @Test
  public void testNewtonianPhysicsAdvance_Horizontal() {

    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorFriction.start(200, 200, 20, 36, 2.0, 0.0);
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_RIGHT_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_LEFT_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorFriction.getStatus());

    assertEquals(List.of(200, 380, 20), ballX);
    assertEquals(List.of(200, 200, 200), ballY);
  }

  @Test
  public void testNewtonianPhysicsAdvance_Vertical() {

    List<Integer> ballX = new ArrayList<>(), ballY = new ArrayList<>();

    poolSimulatorFriction.start(200, 200, 20, 36, 0.0, 3.0);
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_TOP_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_BOTTOM_EDGE, poolSimulatorFriction.getStatus());
    addXY(ballX, ballY, poolSimulatorFriction);
    poolSimulatorFriction.advance();
    assertEquals(STATUS + STATUS_STATIONARY, poolSimulatorFriction.getStatus());

    assertEquals(List.of(200, 380, 20), ballY);
    assertEquals(List.of(200, 200, 200), ballX);
  }

  @Test
  public void getTableWidth() {
    assertEquals(400, poolSimulatorFriction.getTableWidth());
  }

  @Test
  public void getTableHeight() {
    assertEquals(400, poolSimulatorFriction.getTableHeight());
  }

  @Test
  public void getBallPositionX_NotInitialized() {
    assertEquals(Double.NEGATIVE_INFINITY, poolSimulatorSimple.getBallPositionX(), 0);
  }

  @Test
  public void getBallPositionY() {
    assertEquals(Double.NEGATIVE_INFINITY, poolSimulatorSimple.getBallPositionY(), 0);
  }

  @Test
  public void getBallRadius() {
    assertEquals(Double.NEGATIVE_INFINITY, poolSimulatorSimple.getBallRadius(), 0);
  }

  @Test
  public void getBallVelocityX() {
    assertEquals(0, poolSimulatorFriction.getBallVelocityX(), 0);
  }

  @Test
  public void getBallVelocityY() {
    assertEquals(0, poolSimulatorFriction.getBallVelocityY(), 0);
  }

  private void addXY(List<Integer> ballX, List<Integer> ballY, PoolSimulator poolSimulatorSimple) {
    ballX.add((int) poolSimulatorSimple.getBallPositionX());
    ballY.add((int) poolSimulatorSimple.getBallPositionY());
  }

}