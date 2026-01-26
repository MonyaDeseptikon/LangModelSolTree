package deseptikon.monya.db;

import org.h2.tools.Server;

import java.sql.*;

import static deseptikon.monya.db.ConnectionDB.closeCon;
import static deseptikon.monya.db.ConnectionDB.getConnections;

public class CreateDB {


    public static void main(String[] args) throws SQLException {

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
                "OKTMO VARCHAR(20) NULL, " +
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

//        -- Source - https://stackoverflow.com/a/57949317
//        -- Posted by Evgenij Ryazanov
//                -- Retrieved 2025-12-21, License - CC BY-SA 4.0

        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 DROP COLUMN IF EXISTS PREDICTED_01_010;");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS PREDICTED_USAGE_CODE VARCHAR(15) NULL;");

        closeCon(con);
    }



}

