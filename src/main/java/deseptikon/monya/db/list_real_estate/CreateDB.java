package deseptikon.monya.db.list_real_estate;

import java.sql.*;

import static deseptikon.monya.db.list_real_estate.ConnectionDB.closeCon;
import static deseptikon.monya.db.list_real_estate.ConnectionDB.getConnections;

public class CreateDB {


    public static void main(String[] args) throws SQLException {
       CreateDB createDB = new CreateDB();

//        createDB.createNewDB();
//        CreateDB.erasePredictedUC();
    }


    private void createNewDB() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        statement.execute("DROP TABLE PARCELS.PRIVISIONAL_2026");
//        statement.execute("DROP SCHEMA PARCELS");
//        statement.execute("CREATE SCHEMA IF NOT EXISTS PARCELS");
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        statement.execute("CREATE TABLE IF NOT EXISTS PARCELS.PRIVISIONAL_2026 " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "area FLOAT NULL, " +
                "OKATO VARCHAR(20) NULL, " +
                "KLADR VARCHAR(20) NULL, " +
                "locality VARCHAR(MAX) NULL, " +
                "other VARCHAR(MAX) NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "approval_document_name VARCHAR(MAX) NULL, " +
                "category VARCHAR(MAX) NULL, " +
                "utilization_land_use VARCHAR(MAX) NULL, " +
                "utilization_by_doc VARCHAR(MAX) NULL, " +
                "utilization_permitted_use_text VARCHAR(MAX) NULL, " +
                "inner_cadastral_numbers VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL" +
                ");");

        closeCon(con);
    }

    public static void erasePredictedUC() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 DROP COLUMN IF EXISTS PREDICTED_USAGE_CODE;");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS PREDICTED_USAGE_CODE VARCHAR(15) DEFAULT ('');");

        closeCon(con);
    }

}

