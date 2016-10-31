package com.clouway.task3;

import com.clouway.task3.adapter.ConnectionProvider;
import com.clouway.task3.adapter.PersistentStockRepository;
import com.clouway.task3.core.Stock;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class StockRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK3");
  private PersistentStockRepository stockRepository = new PersistentStockRepository(connectionProvider);

  @Before
  public void setup() {
    deleteTable("STOCK");
  }

  @Test
  public void happyPath() throws Exception {
    Stock apple = new Stock("apple", 1.2, 3.5);
    Stock pear = new Stock("pear", 2.2, 4.6);
    stockRepository.register(apple);
    stockRepository.register(pear);

    Stock expected = new Stock("apple", 1.2, 3.5);
    Optional<Stock> actual = stockRepository.find("apple");

    assertThat(actual.get(), is(expected));
  }

  @Test
  public void updatingQuantity() throws Exception {
    Stock apple = new Stock("apple", 1.2, 3.5);
    stockRepository.register(apple);
    Stock expected=new Stock("apple",1.2,4.5);
    stockRepository.updateQuantity("apple",4.5);
    Optional<Stock> actual=stockRepository.find("apple");

    assertThat(actual.get(),is(expected));

  }

  private void deleteTable(String name) {
    Connection connection = connectionProvider.get();
    String query = "DELETE FROM " + name;
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
