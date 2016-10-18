package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface HistoryRepository {
  List<Stock> fullHistory();

  List<Stock> viewPage(Integer page);
}
