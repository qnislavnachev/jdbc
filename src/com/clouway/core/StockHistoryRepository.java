package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface StockHistoryRepository {
  List<Stock> getAll();

  List<Stock> getPages(Integer limit, Integer offset);
}
