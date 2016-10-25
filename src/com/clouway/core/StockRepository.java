package com.clouway.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface StockRepository {
  void register(Stock stock);

  Optional<Stock> find(String name);

  void updateQuantity(String name,Double newQuantity);

}
