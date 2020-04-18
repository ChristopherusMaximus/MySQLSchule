package de.gbschulen.schulemysql;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class KlasseTest {
    @Test
    public void createAndReadKlasse(){

        Database database = new Database();
        database.dropTableKlasse();
        database.createTableKlasse();

        IRepository klasseRepository = new Repository(database.getConnection());
        //CREATE
        Klasse i3a = new Klasse("I3A");
        Klasse e3a = new Klasse("E3A");
        Klasse m3a = new Klasse("M3A");
        klasseRepository.addKlasse(i3a);
        klasseRepository.addKlasse(e3a);
        klasseRepository.addKlasse(m3a);


        //READ
        List<Klasse> klassenList = klasseRepository.getAllKlassen();
        Assert.assertEquals(3,klassenList.size());
    }
}
