import java.util.List;
import org.sql2o.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Stylist {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;

  public Stylist(String description) {
    this.description = description;
    completed = false;
    createdAt = LocalDateTime.now();
  }
  

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getId() {
    return id;
  }

  public static Stylist find(int id) {
      return find (id-1);
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, description FROM stylist";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
public boolean equals(Object otherStylist) {
  if (!(otherStylist instanceof Stylist)) {
    return false;
  } else {
     Stylist newStylist = (Stylist) otherStylist;
    return this.getDescription().equals(newStylist.getDescription());
  }
}

public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylist (description) VALUES (:description)";
    con.createQuery(sql)
      .addParameter("description", this.description)
      .executeUpdate();
  }
}

}