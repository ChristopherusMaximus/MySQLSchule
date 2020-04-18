package de.gbschulen.schulemysql;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Database database = new Database();
        database.dropTableSchueler();
        database.createTableSchueler();

        //Klasse
        database.dropTableKlasse();
        database.createTableKlasse();

        database.dropTableLehrer();
        database.createTableLehrer();

        /*Abschnitt Fach*/
        database.dropTableFach();
        database.createTableFach();

        IRepository repository = new Repository(database.getConnection());


        // CREATE
        Schueler anna = new Schueler("Anna", "Orm");
        repository.addSchueler(anna);

        Schueler bert = new Schueler("Bertram", "Bayer");
        repository.addSchueler(bert);
        repository.addSchueler(new Schueler("Carla", "Kopf"));

        /*Abschnitt Fach*/
        Fach mathe = new Fach("Mathe", false);
        repository.addFach(mathe);

        Fach db = new Fach("Daenbanken", true);
        repository.addFach(db);

        Fach ba = new Fach("Betriebssysteme", true);
        repository.addFach(ba);


        Klasse i3a = new Klasse("I3A");

        Lehrer meier = new Lehrer("Hans", "Meier");
        repository.addLehrer(meier);


        //UPDATE
        anna.setNachname("Blub");
        repository.updateSchueler(anna);



        /*i3a.setName("I4A");
        klasseRepository.updateKlasse(i3a);*/


        /*Abschnitt Fach*/
        db.setName("Datenbanken");
        repository.updateFach(db);


        // READ
        List<Schueler> list = repository.getAllSchuelerSortedByNachname();
        for (Schueler schueler : list) {
            System.out.println(schueler.getNachname() + " " + schueler.getVorname() + " " + schueler.getId());
        }


        List<Klasse> klassenList = repository.getAllKlassen();
        for (Klasse klasse : klassenList) {
            System.out.println(klasse.getName() + "  " + klasse.getId());

        }



        /*Abschnitt Fach*/
        //READ
        List<Fach> fachlist = repository.getAllFaecherSortedByName();
        for (Fach fach : fachlist) {
            System.out.println(fach.getName() + " " + fach.isPflichtfach() + " " + fach.getId());
        }


        //DELETE
        repository.removeSchueler(bert);

        list = repository.getAllSchuelerSortedByVorname();
        for (Schueler schueler : list) {
            System.out.println(schueler.getNachname() + " " + schueler.getVorname() + " " + schueler.getId());
        }


        repository.removeKlasse(i3a);

        klassenList = repository.getAllKlassen();
        for (Klasse klasse : klassenList) {
            System.out.println(klasse.getName() + "  " + klasse.getId());

            /*Abschnitt Fach*/
            repository.removeFach(ba);

            fachlist = repository.getAllFaecherSortedByName();
            for (Fach fach : fachlist) {
                System.out.println(fach.getName() + " " + fach.isPflichtfach() + " " + fach.getId());

            }
        }
    }
}
