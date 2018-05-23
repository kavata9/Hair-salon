import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Agnes", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client myClient = new Client("Agnes", 1);
    assertEquals("Agnes", myClient.getName());
  }


  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Agnes", 1);
    firstClient.save();
    Client secondClient = new Client("Musyoka", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  // @Test
  // public void clear_emptiesAllTasksFromArrayList_0() {
  //   Task myTask = new Task("Mow the lawn", 1);
  //   assertEquals(Task.all().size(), 0);
  // }

  @Test
  public void getId_clientsInstantiateWithAnID_1() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }

  @Test
  public void find_returnsClientsWithSameId_secondTask() {
    Client firstClient = new Client("Agnes", 1);
    firstClient.save();
    Client secondClient = new Client("Musyoka", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Agnes", 1);
    Client secondClient = new Client("Agnes", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("Agnes");
    myStylist.save();
    Client myClient = new Client("Agnes", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void update_updatesClientName_true() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    myClient.update("Musyoka");
    assertEquals("Musyoka", Client.find(myClient.getId()).getName());
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Agnes", 1);
    myClient.save();
    int myClientId = myClient.getId();
    myClient.delete();
    assertEquals(null, Client.find(myClientId));
  }

}
