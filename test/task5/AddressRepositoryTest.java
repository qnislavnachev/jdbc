package task5;

import com.clouway.task5.adapter.ConnectionProvider;
import com.clouway.task5.adapter.PersistenAddressRepository;
import com.clouway.task5.core.Address;
import com.clouway.task5.datastore.DataStore;
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
public class AddressRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK5");
  private DataStore dataStore = new DataStore(connectionProvider);
  private PersistenAddressRepository addressRepository= new PersistenAddressRepository(dataStore);

  @Before
  public void setup() {
    delete();
    Address address = new Address("Vasil","Pleven","Storgozia","Storgozia",51,"D");
    Address address2 = new Address("Martin","Tarnovo","bul Bulgaria","Triagalnik",23,"A");
    addressRepository.register(address);
    addressRepository.register(address2);
  }

  @Test
  public void happyPath() throws Exception {
    List<Address> expected=new LinkedList<>();
    expected.add(new Address("Martin","Tarnovo","bul Bulgaria","Triagalnik",23,"A"));
    expected.add( new Address("Vasil","Pleven","Storgozia","Storgozia",51,"D"));
    List<Address> actual=addressRepository.getAll();
    assertThat(actual,is(expected));

  }

  private void delete() {
    String query = "DELETE FROM ADDRESS";
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
