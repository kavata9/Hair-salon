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
    Client myClient = new Client("Victor");
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstClient = new Client("Agnes");
    Client secondClient = new Client("Agnes");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client myClient = new Client("Agnes");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Agnes");
    firstClient.save();
    Client secondClient = new Client("Mary");
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Agnes");
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void getId_clientInstantiateWithAnID() {
    Client myClient = new Client("Agnes");
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }
  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client(" Agnes");
    firstClient.save();
    Client secondClient = new Client("Mary");
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }
}