package com.clouway.adapter;

import com.clouway.core.StockRepository;
import com.clouway.core.Provider;
import com.clouway.core.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentStockRepository implements StockRepository {
  private Provider<Connection> provider;

  public PersistentStockRepository(Provider<Connection> provider) {
    this.provider = provider;
  }

  @Override
  public void register(Stock stock) {
    Connection connection = provider.get();
    String query = "INSERT INTO STOCK VALUES(?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, stock.name);
      preparedStatement.setDouble(2, stock.price);
      preparedStatement.setDouble(3, stock.quantity);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void updateQuantity(String name, Double newQuantity) {
    Connection connection = provider.get();
    String query = "UPDATE STOCK SET QUANTITY=? WHERE NAME=?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, newQuantity);
      preparedStatement.setString(2, name);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void fillUp(Integer amount) {
    Connection connection = provider.get();
    String query = "INSERT INTO STOCK VALUES(?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      for (Integer i=0;i<amount;i++){
        String name="Legion";
        Double price=i.doubleValue();
        Double quantity=66.6;
        preparedStatement.setString(1,name);
        preparedStatement.setDouble(2,price);
        preparedStatement.setDouble(3,quantity);
        preparedStatement.execute();
        System.out.println("Inserted "+i);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

