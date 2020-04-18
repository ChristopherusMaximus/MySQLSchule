package de.gbschulen.schulemysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private final Connection connection;

    public Database() {
        this.connection = initConnection();
    }

    private Connection initConnection() {
        String databaseName = "schule";
        String user = "schule";
        String password = "geheim";
        String dbString = "jdbc:mysql://localhost:3306/" +
                databaseName +
                "?useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&" +
                "serverTimezone=UTC";

        try {
            Connection connection = DriverManager.getConnection(dbString, user, password);
            System.out.println("Verbindung mit Datenbank steht");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void dropTableSchueler() {
        String sql = "DROP TABLE IF EXISTS schueler";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropTableKlasse() {
        String sql = "DROP TABLE IF EXISTS klasse";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTableSchueler() {
        String sql = "CREATE TABLE schueler " +
                "(id CHAR(36) PRIMARY KEY, vorname VARCHAR(50), nachname VARCHAR(50), id_klasse CHAR(36))";
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createTableKlasse(){
        String sql = "CREATE TABLE klasse (id CHAR(36) PRIMARY KEY, name VARCHAR(50))";
    try {
PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*Abschnitt Fach*/

    public void dropTableFach() {
        String sql = "DROP TABLE IF EXISTS fach";
try {

            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void dropTableLehrer() {
        String sql = "DROP TABLE IF EXISTS lehrer";

    try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    


    public void createTableFach() {
        String sql = "CREATE TABLE fach " +
                "(id CHAR(36) PRIMARY KEY, name VARCHAR(50), pflichtfach TINYINT )";
try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public void createTableLehrer() {
        String sql = "CREATE TABLE lehrer " +
                "(id CHAR(36) PRIMARY KEY, vorname VARCHAR(50), nachname VARCHAR(50) )";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
