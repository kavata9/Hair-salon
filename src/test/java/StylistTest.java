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
    public void getName_StylistInstantiatesWithName_Braider() {
        Stylist testStylist = new Stylist("Breider");
        assertEquals("Breider", testStylist.getName());
    }

    @Test
      public void equals_returnsTrueIfNamesAretheSame() {
        Stylist firstStylist = new Stylist("Breider");
        Stylist secondStylist = new Stylist("Breider");
        assertTrue(firstStylist.equals(secondStylist));
      }
}
        