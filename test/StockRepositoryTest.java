import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentStockRepository;
import com.clouway.core.Stock;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class StockRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK3");
  private PersistentStockRepository stockRepository = new PersistentStockRepository(connectionProvider);

  @Before
  public void setup() {
    deleteTable("STOCK");
    Stock apple = new Stock("apple", 1.2, 3.5);
    Stock pear = new Stock("pear", 2.2, 4.6);
    stockRepository.register(apple);
    stockRepository.register(pear);
  }

  @Test
  public void happyPath() throws Exception {


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
