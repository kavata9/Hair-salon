import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void category_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Agnes");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_Agnes() {
    Stylist testStylist = new Stylist("Agnes");
    assertEquals("Agnes", testStylist.getName());
  }

 @Test
 public void all_returnsAllInstancesOfStylist_true() {
   Stylist firstStylist = new Stylist("Agnes");
   firstStylist.save();
   Stylist secondStylist = new Stylist("Mercy");
   secondStylist.save();
   assertEquals(true, Stylist.all().get(0).equals(firstStylist));
   assertEquals(true, Stylist.all().get(1).equals(secondStylist));
 }

 @Test
 public void getId_stylistsInstantiateWithAnId_1() {
   Stylist testStylist = new Stylist("Agnes");
   testStylist.save();
   assertTrue(testStylist.getId() > 0);
 }

 @Test
 public void find_returnsStylistWithSameId_secondStylist() {
   Stylist firstStylist = new Stylist("Agnes");
   firstStylist.save();
   Stylist secondStylist = new Stylist("Mercy");
   secondStylist.save();
   assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
 }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Agnes");
    Stylist secondStylist = new Stylist("Agnes");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Agnes");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Agnes");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getClients_retrievesALlClientsFromDatabase_clientList() {
    Stylist myStylist = new Stylist("Agnes");
    myStylist.save();
    Client firstClient = new Client("Agnes", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Musyoka", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }

}
