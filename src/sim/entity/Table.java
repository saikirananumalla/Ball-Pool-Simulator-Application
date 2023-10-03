package sim.entity;

/**
 * A class to represent table with axis starting at (0,0) coordinate in XY plane.
 */
public class Table {

  private final int x;

  private final int y;

  private final TableType type;

  /**
   * Create table entity with given xMax and yMax values.
   *
   * @param x    xMax of the table rectangle
   * @param y    yMax of the table rectangle
   * @param type type of the table whether simple or friction
   */
  public Table(int x, int y, String type) {
    this.x = x;
    this.y = y;
    this.type = TableType.getByString(type);
  }

  /**
   * Gets the xMax value of the table.
   *
   * @return xMax
   */
  public int getXMax() {
    return x;
  }

  /**
   * Gets the yMax value of the table.
   *
   * @return yMax
   */
  public int getYMax() {
    return y;
  }

  public TableType getType() {
    return type;
  }
}
