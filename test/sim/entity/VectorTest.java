package sim.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Junit class to test to the Vector class.
 */
public class VectorTest {

  private Vector vector;

  @Before
  public void setUp() throws Exception {
    vector = new Vector(3.2, 0.9);
  }

  @Test
  public void getX() {
    assertEquals(3.2, vector.getX(), 0);
  }

  @Test
  public void getY() {
    assertEquals(0.9, vector.getY(), 0);
  }

  @Test
  public void normalize() {
    vector.normalize();
    assertEquals(0.962650940153899, vector.getX(), 0);
    assertEquals(0.27074557691828405, vector.getY(), 0);
  }

  @Test
  public void invertX() {
    vector.invertX();
    assertEquals(-3.2, vector.getX(), 0);
  }

  @Test
  public void invertY() {
    vector.invertY();
    assertEquals(-0.9, vector.getY(), 0);
  }

  @Test
  public void add() {
    vector.add(-1.2, 1.1);
    assertEquals(2, vector.getX(), 0);
    assertEquals(2, vector.getY(), 0);
  }
}