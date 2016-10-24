package com.clouway.core;


import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface Provider<T> {
  T get();
}
