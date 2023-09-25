package sim.entity;

/**
 * A class to represent table with axis starting at (0,0) coordinate in XY plane.
 */
public class Table {

  private final int x;

  private final int y;

  /**
   * Create table entity with given xMax and yMax values.
   *
   * @param x xMax of the table rectangle
   * @param y yMax of the table rectangle
   */
  public Table(int x, int y) {
    this.x = x;
    this.y = y;
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

}
