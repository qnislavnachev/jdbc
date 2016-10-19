package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface StockHistoryRepository {
  List<Stock> getAll();

  List<Stock> viewPage(Integer page);

//LIst<S> getPages(int limit ,int offset)
}
