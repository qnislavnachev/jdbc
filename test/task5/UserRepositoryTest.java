package task5;

import com.clouway.task5.adapter.ConnectionProvider;
import com.clouway.task5.adapter.PersistentUserRepository;
import com.clouway.task5.adapter.core.User;
import com.clouway.task5.adapter.datastore.DataStore;
import org.junit.Before;
import org.junit.Test;

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
public class UserRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK5");
  private DataStore dataStore = new DataStore(connectionProvider);
  private PersistentUserRepository userRepository = new PersistentUserRepository(dataStore);

  @Before
  public void setup() {
    delete();
    User user = new User("Vasil", 21, "admin");
    User user1 = new User("Martin", 22, "super user");
    userRepository.register(user);
    userRepository.register(user1);
  }

  @Test
  public void happyPath() throws Exception {
    List<User> expected=new LinkedList<>();
    expected.add(new User("Martin", 22, "super user"));
    expected.add(new User("Vasil", 21, "admin"));
    List<User> actual=userRepository.getAll();
    assertThat(actual,is(expected));

  }

  private void delete() {
    String query = "DELETE FROM USER";
    Connection connection = connectionProvider.get();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
}
