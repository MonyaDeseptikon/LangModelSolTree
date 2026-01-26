package deseptikon.monya.parcels.db.create_tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class BuildingCreateTable {


    public static void main(String[] args) throws SQLException {
        BuildingCreateTable createSomething = new BuildingCreateTable();
        createSomething.createScheme();
        createSomething.createTable();
    }

    private void createScheme() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
//Не будет удаляться, пока не удалить все таблицы, либо удалять сразу пользователя (это для БД H2)
//        statement.execute("DROP SCHEMA IF EXISTS BUILDINGS");
        statement.execute("DROP SCHEMA BUILDINGS CASCADE"); // ?????????????? Будет ли это работать неизвестно

        statement.execute("CREATE SCHEMA IF NOT EXISTS BUILDINGS");
    }

    private void createTable() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS BUILDINGS.PARCEL_INNER_CN " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "object_type VARCHAR(MAX) NULL, " +
                "object_name VARCHAR(MAX) NULL, " +
                "object_assignation VARCHAR(MAX) NULL, " +
                "object_permitted_uses VARCHAR(MAX) NULL, " +
                "OKATO VARCHAR(20) NULL, " +
                "OKTMO VARCHAR(20) NULL, " +
                "area FLOAT NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL, " +
                "parcel_cadastral_numbers VARCHAR(MAX) NULL" +
                ");");
        closeCon(con);
    }

}

