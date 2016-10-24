import com.clouway.adapter.ConnectionProvider;
import com.clouway.datastore.DataStore;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class UserRepositoryTest {
  private ConnectionProvider connectionProvider=new ConnectionProvider("TASK5");
  private DataStore dataStore=new DataStore(connectionProvider);
}
