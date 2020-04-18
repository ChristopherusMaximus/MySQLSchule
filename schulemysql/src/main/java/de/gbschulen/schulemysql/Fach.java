package de.gbschulen.schulemysql;

import java.util.UUID;

public class Fach {
    private final UUID id;
    private String name;
    private boolean pflichtfach;

    public Fach(String name, boolean pflichtfach) {
        this(name, pflichtfach, UUID.randomUUID());
    }

    public Fach(String name, boolean pflichtfach, UUID id) {
        this.name = name;
        this.pflichtfach = pflichtfach;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPflichtfach() {
        return pflichtfach;
    }

    public void setPflichtfach(boolean pflichtfach) {
        this.pflichtfach = pflichtfach;
    }
}
