package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface TableManager {
  <T> List<T> display();

  void delete();

  void addColumn(String columnName, String type);

}
