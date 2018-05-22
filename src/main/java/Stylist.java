import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
    private String name;
    // private String speciality;
    private int id;

    public Stylist(String name) {
        this.name = name;
        // this.speciality =  speciality;
    }

    public String getName() {
        return name;
    }

    public static List<Stylist> all() {
        String sql = "SELECT id, name FROM stylist";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
      }

    public int getId() {
        return id;
    }

    // public List<Client> getClients() {
    // }

    // public static Stylist find(int id) {
    // }

    @Override
    public boolean equals(Object otherStylist) {
      if (!(otherStylist instanceof Stylist)) {
        return false;
      } else {
        Stylist newStylist = (Stylist) otherStylist;
        return this.getName().equals(newStylist.getName());
      }
    }

}