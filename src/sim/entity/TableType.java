package sim.entity;

/**
 * An enum indicate type of table, whether simple or friction.
 */
public enum TableType {
  SIMPLE("simple"),
  FRICTION("friction");

  private final String status;

  TableType(String status) {
    this.status = status;
  }

  /**
   * Get enum as string value.
   *
   * @return string
   */
  public String asString() {
    return this.status;
  }


  /**
   * Get enum from string.
   *
   * @param s string value
   * @return enum TableType
   */
  public static TableType getByString(String s) {
    for (TableType t : TableType.values()) {
      if (t.asString().equals(s)) {
        return t;
      }
    }
    return null;
  }
}
