package com.clouway.adapter;

import com.clouway.core.HistoryRepository;
import com.clouway.core.Provider;
import com.clouway.core.Stock;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentHistoryRepository implements HistoryRepository {
  private Provider<Connection> provider;
  private Integer pageSize;

  public PersistentHistoryRepository(Provider<Connection> provider, Integer pageSize) {
    this.provider = provider;
    this.pageSize = pageSize;
  }

  @Override
  public List<Stock> fullHistory() {
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
  public List<Stock> viewPage(Integer page) {
    Connection connection = provider.get();
    Integer limit = (page * pageSize) -2;
    String query = "SELECT * FROM STOCK_HISTORY LIMIT ? , ?";
    List<Stock> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, pageSize);
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
