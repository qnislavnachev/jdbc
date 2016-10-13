package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface StockRepository {
  void register(Stock stock);

  void updateQuantity(String name,Double newQuantity);

}
