package task5;

import com.clouway.task5.adapter.ConnectionProvider;
import com.clouway.task5.adapter.PersistentContactRepository;
import com.clouway.task5.adapter.core.Contact;
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
public class ContactRepositoryTest {
  private ConnectionProvider connectionProvider=new ConnectionProvider("TASK5");
  private DataStore dataStore=new DataStore(connectionProvider);
  private PersistentContactRepository contactRepository=new PersistentContactRepository(dataStore);

  @Before
  public void setup(){
    Contact contact=new Contact("Vasil","0887095662","alroy@abv.bg");
    Contact contact1=new Contact("Martin","0882345622","marto@abv.bg");
    contactRepository.register(contact);
    contactRepository.register(contact1);
  }

  @Test
  public void happyPath() throws Exception {
    List<Contact> actual=new LinkedList<>();
    actual.add(new Contact("Martin","0882345622","marto@abv.bg"));
    actual.add(new Contact("Vasil","0887095662","alroy@abv.bg"));
    List<Contact> expected=contactRepository.getAll();
    assertThat(actual,is(expected));

  }

  private void delete() {
    String query = "DELETE FROM CONTACT";
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
