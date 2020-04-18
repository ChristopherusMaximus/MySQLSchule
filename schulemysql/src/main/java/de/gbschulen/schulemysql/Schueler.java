package de.gbschulen.schulemysql;

import java.util.UUID;

public class Schueler {
    private String vorname;
    private String nachname;
    private final UUID id;

    public Schueler(String vorname, String nachname) {
        this(vorname, nachname, UUID.randomUUID());
    }

    public Schueler(String vorname, String nachname, UUID id) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public UUID getId() {
        return id;
    }

}
