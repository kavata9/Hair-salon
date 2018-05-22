import org.sql2o.*;
    import org.junit.*;
    import static org.junit.Assert.*;

    public class StylistTest {

      @Before
      public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "agnes", "a9%Mrdvs");
      }

      @After
      public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
          String deleteClientQuery = "DELETE FROM client *;";
          String deleteStylistQuery = "DELETE FROM stylist *;";
          con.createQuery(deleteClientQuery).executeUpdate();
          con.createQuery(deleteStylistQuery).executeUpdate();
        }
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
      public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("Agnes");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Mary");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
      }

      @Test
      public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("Agnes");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
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
    }