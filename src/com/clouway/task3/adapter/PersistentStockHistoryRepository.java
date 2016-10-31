package com.clouway.task3.adapter;

import com.clouway.task3.core.StockHistoryRepository;
import com.clouway.task3.core.Provider;
import com.clouway.task3.core.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentStockHistoryRepository implements StockHistoryRepository {
  private Provider<Connection> provider;

  public PersistentStockHistoryRepository(Provider<Connection> provider) {
    this.provider = provider;
  }

  @Override
  public List<Stock> getAll() {
    Connection connection = provider.get();
    String query = "SELECT * FROM STOCK_HISTORY";
    List<Stock> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString(1);
        Double price = resultSet.getDouble(2);
        Double quantity = resultSet.getDouble(3);
        result.add(new Stock(name, price, quantity));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public List<Stock> getPages(Integer limit, Integer offset) {
    Connection connection = provider.get();
    String query = "SELECT * FROM STOCK_HISTORY LIMIT ? , ?";
    List<Stock> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, offset);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString(1);
        Double price = resultSet.getDouble(2);
        Double quantity = resultSet.getDouble(3);
        result.add(new Stock(name, price, quantity));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
