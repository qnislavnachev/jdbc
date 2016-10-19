import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentHistoryRepository;
import com.clouway.adapter.PersistentStockRepository;
import com.clouway.core.Stock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class HistoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK3");
  private PersistentStockRepository stockRepository = new PersistentStockRepository(connectionProvider);
  private PersistentHistoryRepository historyRepository = new PersistentHistoryRepository(connectionProvider, 2);

  @Before
  public void fillingUp() {
    deleteTable("STOCK_HISTORY");
    deleteTable("STOCK");
    Stock apple = new Stock("apple", 1.2, 3.5);
    Stock pear = new Stock("pear", 2.2, 4.6);
    Stock orange = new Stock("orange", 3.1, 5.1);
    Stock potato = new Stock("potato", 2.3, 6.5);
    Stock tomato = new Stock("tomato", 2.1, 4.1);
    Stock watermelon = new Stock("watermelon", 1.5, 3.9);
    Stock cherry = new Stock("cherry", 0.9, 13.1);
    Stock toothbrush = new Stock("toothbrush", 1.2, 20.0);
    Stock toothpaste = new Stock("toothpaste", 2.8, 7.0);
    Stock headphones = new Stock("headphones", 30.5, 10.0);
    Stock tabaco = new Stock("tabaco", 10.9, 30.0);

    stockRepository.register(apple);
    stockRepository.register(pear);
    stockRepository.register(orange);
    stockRepository.register(potato);
    stockRepository.register(tomato);
    stockRepository.register(watermelon);
    stockRepository.register(cherry);
    stockRepository.register(toothbrush);
    stockRepository.register(toothpaste);
    stockRepository.register(headphones);
    stockRepository.register(tabaco);

    stockRepository.updateQuantity("apple", 1.1);
    stockRepository.updateQuantity("pear", 1.2);
    stockRepository.updateQuantity("orange", 4.2);
    stockRepository.updateQuantity("potato", 6.7);
    stockRepository.updateQuantity("tomato", 3.5);
  }

  @Test
  public void displayFullHistory() throws Exception {
    List<Stock> actual = historyRepository.fullHistory();
    List<Stock> expected = new LinkedList<>();

    expected.add(new Stock("apple", 1.2, 3.5));
    expected.add(new Stock("pear", 2.2, 4.6));
    expected.add(new Stock("orange", 3.1, 5.1));
    expected.add(new Stock("potato", 2.3, 6.5));
    expected.add(new Stock("tomato", 2.1, 4.1));

    assertThat(actual, is(expected));
  }

  @Test
  public void displayInPages() throws Exception {
    List<Stock> actual = historyRepository.viewPage(2);
    List<Stock> expected = new LinkedList<>();

    expected.add(new Stock("orange", 3.1, 5.1));
    expected.add(new Stock("potato", 2.3, 6.5));

    assertThat(actual, is(expected));
  }

  @Test
  public void displayLastPageWithoutHavingenoughtStockToFillIt() throws Exception {
    List<Stock> actual = historyRepository.viewPage(3);
    List<Stock> expected = new LinkedList<>();

    expected.add(new Stock("tomato", 2.1, 4.1));

    assertThat(actual, is(expected));
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
