package deseptikon.monya.parcels.db.create_tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class AuxiliaryTables {


    public static void main(String[] args) throws SQLException {
        AuxiliaryTables auxiliaryTables = new AuxiliaryTables();
        auxiliaryTables.createScheme();
//        auxiliaryTables.createOKTMO();
        auxiliaryTables.createKLADR();

    }

    private void createScheme() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP SCHEMA AUXILIARY CASCADE");
        statement.execute("CREATE SCHEMA IF NOT EXISTS AUXILIARY");
    }

    private void createOKTMO() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS AUXILIARY.OKTMO " +
                //Заготовка
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "building_name VARCHAR(MAX) NULL, " +
                "area FLOAT NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL, " +
                "parcel_cadastral_numbers VARCHAR(MAX) NULL" +
                ");");
        closeCon(con);
    }

    private void createKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS AUXILIARY.KLADR " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "CODE_KLADR VARCHAR(30) NOT NULL, " +
                "TYPE_DISTRICT VARCHAR(MAX) NULL, " +
                "DISTRICT VARCHAR(MAX) NULL, " +
                "CITY VARCHAR(MAX) NULL, " +
                "TYPE_CITY VARCHAR(MAX) NULL, " +
                "LOCALITY VARCHAR(MAX) NULL, " +
                "TYPE_LOCALITY VARCHAR(MAX) NULL, " +
                "STREET VARCHAR(MAX) NULL, " +
                "TYPE_STREET VARCHAR(MAX) NULL" +
                "CODE_OKATO VARCHAR(30) NOT NULL, " +
                ");");
        closeCon(con);
    }

}



