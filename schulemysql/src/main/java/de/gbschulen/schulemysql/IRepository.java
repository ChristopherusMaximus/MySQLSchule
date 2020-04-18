package de.gbschulen.schulemysql;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    // CREATE
    void addSchueler(Schueler schueler);
    void addKlasse(Klasse klasse);
    void addFach(Fach fach);
    void addLehrer(Lehrer lehrer);

    // READ
    List<Schueler> getAllSchuelerSortedByNachname();
    List<Schueler> getAllSchuelerSortedByVorname();
    Schueler getSchuelerById(UUID uuid);
    List<Schueler> getAllSchuelerFromKlasse(Klasse klasse);

    List<Klasse> getAllKlassen();
    List<Fach> getAllFaecherSortedByName();
    List<Lehrer> getAllLehrerSortedByNachname();


    // UPDATE
    void updateSchueler(Schueler schueler);
    void updateKlasse(Klasse klasse);
    void updateFach(Fach fach);
    void updateLehrer(Lehrer lehrer);

    // DELETE
    void removeSchueler(Schueler schueler);
    void removeKlasse(Klasse klasse);
    void removeFach(Fach fach);
    void removeLehrer(Lehrer lehrer);

    // VERKNUEPFUNGEN
    void addSchuelerToKlasse(Schueler schueler, Klasse klasse);
    void removeSchuelerFromKlasse(Schueler schueler, Klasse klasse);
}
