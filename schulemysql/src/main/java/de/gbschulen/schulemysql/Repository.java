package de.gbschulen.schulemysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository implements IRepository {
    private Connection connection;

    public Repository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSchueler(Schueler schueler) {
        String sql = "INSERT INTO schueler(id, vorname, nachname) VALUES (?,?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, schueler.getId().toString());
            statement.setString(2, schueler.getVorname());
            statement.setString(3, schueler.getNachname());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addKlasse(Klasse klasse) {
        String sql = "INSERT INTO klasse(id, name) VALUES (?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, klasse.getId().toString());
            statement.setString(2, klasse.getName());
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addLehrer(Lehrer lehrer) {
        String sql = "INSERT INTO lehrer(id, vorname, nachname) VALUES (?,?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, lehrer.getId().toString());
            statement.setString(2, lehrer.getVorname());
            statement.setString(3, lehrer.getNachname());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Schueler> getAllSchuelerSortedByNachname() {
        List<Schueler> schuelerList = new ArrayList<>();

        String sql = "SELECT * FROM schueler ORDER BY nachname";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                Schueler schueler = new Schueler(vorname,nachname, id);
                schuelerList.add(schueler);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schuelerList;
    }

    @Override
    public List<Schueler> getAllSchuelerSortedByVorname() {
        List<Schueler> schuelerList = new ArrayList<>();

        String sql = "SELECT * FROM schueler ORDER BY vorname";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                Schueler schueler = new Schueler(vorname,nachname, id);
                schuelerList.add(schueler);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schuelerList;
    }

    @Override
    public List<Schueler> getAllSchuelerFromKlasse(Klasse klasse) {
        List<Schueler> schuelerList = new ArrayList<>();
        String sql = "SELECT * FROM schueler WHERE id_klasse = ? ORDER BY nachname";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, klasse.getId().toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                Schueler schueler = new Schueler(vorname,nachname, id);
                schuelerList.add(schueler);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schuelerList;
    }

    @Override
    public void addSchuelerToKlasse(Schueler schueler, Klasse klasse) {
        String sql = "UPDATE schueler SET id_klasse = ? WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, klasse.getId().toString());
            statement.setString(2, schueler.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSchuelerFromKlasse(Schueler schueler, Klasse klasse) {
        String sql = "UPDATE schueler SET id_klasse = null WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, schueler.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Klasse> getAllKlassen() {
        List<Klasse> klassenListe = new ArrayList<>();

        String sql = "SELECT * FROM klasse;";
      try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));

                String name = rs.getString("name");

                Klasse klasse = new Klasse(name,id);
                klassenListe.add(klasse); 
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return klassenListe;
    }
    
    @Override
    public List<Lehrer> getAllLehrerSortedByNachname() {
        List<Lehrer> lehrerList = new ArrayList<>();

        String sql = "SELECT * FROM lehrer ORDER BY nachname";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));              
                

                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                Lehrer lehrer = new Lehrer(vorname,nachname, id);
                lehrerList.add(lehrer);

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        


        return lehrerList;
    }

    @Override
    public Schueler getSchuelerById(UUID uuid) {
        Schueler schueler = null;
        String sql = "SELECT * FROM schueler WHERE id=?";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                schueler = new Schueler(vorname, nachname, id);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schueler;

    }

     @Override
    public void updateSchueler(Schueler schueler) {
        String sql = "UPDATE schueler SET vorname = ?, nachname = ? WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, schueler.getVorname());
            statement.setString(2, schueler.getNachname());
            statement.setString(3, schueler.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateKlasse(Klasse klasse) {
        String sql = "UPDATE klasse SET name = ? WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, klasse.getName());
            statement.setString(2, klasse.getId().toString());
          statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public void updateLehrer(Lehrer lehrer) {
        String sql = "UPDATE lehrer SET vorname = ?, nachname = ? WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, lehrer.getVorname());
            statement.setString(2, lehrer.getNachname());
            statement.setString(3, lehrer.getId().toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void removeSchueler(Schueler schueler) {
        String sql = "DELETE FROM schueler WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, schueler.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*Abschnitt Fach*/

    @Override
    public void addFach(Fach fach) {
        String sql = "INSERT INTO fach(id, name, pflichtfach) VALUES (?,?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, fach.getId().toString());
            statement.setString(2, fach.getName());
            statement.setBoolean(3, fach.isPflichtfach());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fach> getAllFaecherSortedByName() {
        List<Fach> faecherList = new ArrayList<>();

        String sql = "SELECT * FROM fach ORDER BY name";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                Boolean pflichtfach = rs.getBoolean("pflichtfach");
                Fach fach = new Fach(name, pflichtfach, id);
                faecherList.add(fach);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faecherList;
    }


    @Override
    public void updateFach(Fach fach) {
        String sql = "UPDATE fach SET name = ?, pflichtfach = ? WHERE id = ?;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, fach.getName());
            statement.setBoolean(2, fach.isPflichtfach());
            statement.setString(3, fach.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeKlasse(Klasse klasse) {
        String sql = "DELETE FROM klasse WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, klasse.getId().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLehrer(Lehrer lehrer) {
        String sql = "DELETE FROM lehrer WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, lehrer.getId().toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFach(Fach fach) {
        String sql = "DELETE FROM fach WHERE id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, fach.getId().toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
