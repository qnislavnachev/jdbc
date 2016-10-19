import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentStockRepository;
import org.junit.Test;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Testing {
  private ConnectionProvider connectionProvider=new ConnectionProvider("TASK4");
  private PersistentStockRepository stockRepository=new PersistentStockRepository(connectionProvider);

  @Test
  public void fillup() throws Exception {
    stockRepository.fillUp(1000000);

  }
}
