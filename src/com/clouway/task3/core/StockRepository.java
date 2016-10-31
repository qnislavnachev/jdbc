package com.clouway.task3.core;

import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface StockRepository {
  void register(Stock stock);

  Optional<Stock> find(String name);

  void updateQuantity(String name,Double newQuantity);

}
