import java.util.List;
import org.sql2o.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;

  public Client(String description) {
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

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM client where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }
//database initiation
  public static List<Client> all() {
    String sql = "SELECT id, description FROM client";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getDescription().equals(newClient.getDescription()) &&
             this.getId() == newClient.getId();
    }
  }

public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO client(description) VALUES (:description)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("description", this.description)
      .executeUpdate()
      .getKey();
  }
}

}