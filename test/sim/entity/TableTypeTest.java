package sim.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static sim.util.Constants.FRICTION;
import static sim.util.Constants.SIMPLE;

/**
 * A JUnit class to test the table type class.
 */
public class TableTypeTest {

  private TableType simpleTable;

  private TableType frictionTable;

  @Before
  public void setUp() {
    simpleTable = TableType.SIMPLE;
    frictionTable = TableType.FRICTION;
  }

  @Test
  public void asString() {
    assertEquals(SIMPLE, simpleTable.asString());
    assertEquals(FRICTION, frictionTable.asString());
  }

  @Test
  public void getByString() {
    assertEquals(TableType.FRICTION, TableType.getByString(FRICTION));
    assertEquals(TableType.SIMPLE, TableType.getByString(SIMPLE));
  }
}