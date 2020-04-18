package de.gbschulen.schulemysql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SchuelerKlasseTest {
    private Database database;
    private IRepository repository;

    //Init Database and repository before every test
    @Before
    public void init() {
        //create database object
        database = new Database();
        //drop tables
        database.dropTableSchueler();
        database.dropTableKlasse();
        //create tables
        database.createTableSchueler();
        database.createTableKlasse();

        //create repository object
        repository = new Repository(database.getConnection());
    }

    @Test
    public void getAllSchuelerFromKlasseTest(){
        // CREATE
        Schueler anna = new Schueler("Anna", "Orm");
        repository.addSchueler(anna);
        Schueler bert = new Schueler("Bertram", "Bayer");
        repository.addSchueler(bert);
        Schueler carla = new Schueler("Carla", "Kopf");
        repository.addSchueler(carla);
        Klasse i3a = new Klasse("I3A");
        Klasse e3a = new Klasse("E3A");
        Klasse m3a = new Klasse("M3A");
        repository.addKlasse(i3a);
        repository.addKlasse(e3a);
        repository.addKlasse(m3a);

        // add Schueler to Klasse
        repository.addSchuelerToKlasse(anna, i3a);
        repository.addSchuelerToKlasse(bert, m3a);
        repository.addSchuelerToKlasse(carla, i3a);

        List<Schueler> result = repository.getAllSchuelerFromKlasse(i3a);

        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void removeSchuelerFromKlasseTest(){
        // CREATE
        Schueler anna = new Schueler("Anna", "Orm");
        repository.addSchueler(anna);
        Schueler bert = new Schueler("Bertram", "Bayer");
        repository.addSchueler(bert);
        Schueler carla = new Schueler("Carla", "Kopf");
        repository.addSchueler(carla);
        Klasse i3a = new Klasse("I3A");
        Klasse e3a = new Klasse("E3A");
        Klasse m3a = new Klasse("M3A");
        repository.addKlasse(i3a);
        repository.addKlasse(e3a);
        repository.addKlasse(m3a);

        // add Schueler to Klasse
        repository.addSchuelerToKlasse(anna, i3a);
        repository.addSchuelerToKlasse(bert, m3a);
        repository.addSchuelerToKlasse(carla, i3a);

        repository.removeSchuelerFromKlasse(anna, i3a);
        List<Schueler> result = repository.getAllSchuelerFromKlasse(i3a);

        Assert.assertEquals(result.size(), 1);
    }
}
