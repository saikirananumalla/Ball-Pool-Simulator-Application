package sim.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit class to test the table class.
 */
public class TableTest {

  private Table table;

  @Before
  public void setUp() {
    table = new Table(10, 10, TableType.FRICTION.asString());
  }

  @Test
  public void getXMax() {
    assertEquals(10, table.getXMax());
  }

  @Test
  public void getYMax() {
    assertEquals(10, table.getYMax());
  }

  @Test
  public void getType() {
    assertEquals(TableType.FRICTION, table.getType());
  }
}