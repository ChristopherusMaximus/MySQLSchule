package de.gbschulen.schulemysql;

import java.util.List;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LehrerTest {

    List<Lehrer> list;

    public void createLehrer(){
        Database database = new Database();
        database.dropTableLehrer();
        database.createTableLehrer();

        IRepository repository = new Repository(database.getConnection());

        // CREATE
        Lehrer meier = new Lehrer("Hans", "Meier");
        repository.addLehrer(meier);
        Lehrer pfahl = new Lehrer("Martha", "Pfahl");
        repository.addLehrer(pfahl);
        Lehrer schweiss = new Lehrer("Axel", "Schweiss");
        repository.addLehrer(schweiss);
        list = repository.getAllLehrerSortedByNachname();

    }

    @Test
    public void countLehrerShouldBe3()
    {
        createLehrer();
        assertTrue(list.size()== 3);
    }


}
