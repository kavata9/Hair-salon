import java.util.List;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Stylist {
    private String name;
    private int id;

    public Stylist(String name) {
        this.name = name;
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

    // public List<Client> getClient() {
    // }

    public static Stylist find(int id) {
        return find(id - 1);
    }

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