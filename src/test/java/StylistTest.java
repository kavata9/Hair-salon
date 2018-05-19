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
      String sql = "DELETE FROM stylist *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  @Test
    public void stylist_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("Braider");
        assertEquals(true, testStylist instanceof Stylist);
    }
    @Test
public void equals_returnsTrueIfDescriptionsAretheSame() {
  Stylist firstStylist = new Stylist("Braider");
  Stylist secondStylist = new Stylist("Braider");
  assertTrue(firstStylist.equals(secondStylist));
}

@Test
public void save_returnsTrueIfDescriptionsAretheSame() {
  Stylist myStylist = new Stylist("Braider");
  myStylist.save();
  assertTrue(Stylist.all().get(0).equals(myStylist));
}

@Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Braider");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Wash and style");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

}
