package de.gbschulen.schulemysql;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Schueler
 */
public class SchuelerTest {


    private Database database;
    private IRepository repository;

    //Init Database and repository before every test
    @Before
    public void init() {
        //create database object
        database = new Database();
        //drop table "Schueler"
        database.dropTableSchueler();
        //create table "Schueler"
        database.createTableSchueler();

        //create repository object
        repository = new Repository(database.getConnection());
    }

    //Test READ methods incl. CREATE method
    @Test
    public void getAllSchuelerSortedByNachnameTest() {

        // CREATE
        Schueler anna = new Schueler("Anna", "Orm");
        repository.addSchueler(anna);

        Schueler bert = new Schueler("Bertram", "Bayer");
        repository.addSchueler(bert);

        Schueler carla = new Schueler("Carla", "Kopf");
        repository.addSchueler(carla);

        // READ
        List<Schueler> result = repository.getAllSchuelerSortedByNachname();

        //Create Expected List
        List<Schueler> expected = new ArrayList<>();
        expected.add(bert);
        expected.add(carla);
        expected.add(anna);

        //Loop over result list
        for (int i = 0; i < result.size(); i++) {

            //get schueler from expected and result list
            Schueler expect = expected.get(i);
            Schueler res = result.get(i);

            //compare the id's
            Assert.assertEquals(expect.getId(), res.getId());


        }
    }

    @Test
    public void getAllSchuelerSortedByVornameTest() {

        // CREATE
        Schueler anna = new Schueler("Anna", "Orm");
        repository.addSchueler(anna);

        Schueler bert = new Schueler("Bertram", "Bayer");
        repository.addSchueler(bert);

        Schueler carla = new Schueler("Carla", "Kopf");
        repository.addSchueler(carla);

        // READ
        List<Schueler> result = repository.getAllSchuelerSortedByVorname();

        //Create Expected List
        List<Schueler> expected = new ArrayList<>();
        expected.add(anna);
        expected.add(bert);
        expected.add(carla);

        //Loop over result list
        for (int i = 0; i < result.size(); i++) {

            //get schueler from expected and result list
            Schueler expect = expected.get(i);
            Schueler res = result.get(i);

            //compare the id's
            Assert.assertEquals(expect.getId(), res.getId());


        }
    }

    @Test
    public void getSchuelerByIdTest() {
        // CREATE expected Schueler and add it to the database
        Schueler expected = new Schueler("Anna", "Orm");
        repository.addSchueler(expected);

        //get Schueler from database identified by id
        Schueler result = repository.getSchuelerById(expected.getId());

        //compare it
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNachname(), result.getNachname());
        assertEquals(expected.getVorname(), result.getVorname());
    }

    //Test UPDATE method
    @Test
    public void updateSchuelerTest() {
        // CREATE expected Schueler and add it to the database
        Schueler expected = new Schueler("Anna", "Orm");
        repository.addSchueler(expected);

        //change vorname and nachname
        expected.setVorname("AnnaGeändert");
        expected.setNachname("OrmGeändert");
        //update the Schueler in the database
        repository.updateSchueler(expected);

        //get result Schueler from database
        Schueler result = repository.getSchuelerById(expected.getId());

        //compare it
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNachname(), result.getNachname());
        assertEquals(expected.getVorname(), result.getVorname());
    }

    //Test REMOVE method
    @Test
    public void removeSchuelerTest() {
        // CREATE dummy user add it to the database and remove it
        Schueler dummy = new Schueler("Anna", "Orm");
        repository.addSchueler(dummy);
        repository.removeSchueler(dummy);

        //try to get a result Schueler
        Schueler result = repository.getSchuelerById(dummy.getId());

        //result Schueler should be null
        assertEquals(null, result);
    }

}
