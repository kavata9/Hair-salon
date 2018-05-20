import com.sun.security.ntlm.Client;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "agnes", "a9%Mrdvs");
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM client *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test

  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Agnes", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstClient = new Client("Agnes", 1);
    Client secondClient = new Client("Agnes", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Agnes", 1);
    firstClient.save();
    Client secondClient = new Client("Mary", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void getId_clientInstantiateWithAnID() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Agnes", 1);
    firstClient.save();
    Client secondClient = new Client("Mary", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("Breider");
    myStylist.save();
    Client myClient = new Client("Agnes", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    int myClientId = myClient.getId();
    myClientId.delete();
    assertEquals(null, Client.find(myClientId));
  }
}