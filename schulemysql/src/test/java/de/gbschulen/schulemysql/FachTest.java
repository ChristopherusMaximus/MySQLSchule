package de.gbschulen.schulemysql;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FachTest {


    @Test
    public void creatReadTest(){
        Database database = new Database();
        IRepository repository = new Repository(database.getConnection());

        database.dropTableFach();
        database.createTableFach();

        Fach mathe = new Fach("Mathe", false);
        repository.addFach(mathe);

        Fach db = new Fach("Daenbanken", true);
        repository.addFach(db);

        Fach ba = new Fach("Betriebssysteme", true);
        repository.addFach(ba);

        List<Fach> fachlist = repository.getAllFaecherSortedByName();
        assertEquals(3, fachlist.size());
        }
    }

